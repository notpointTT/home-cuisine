package com.hc.commons.security.config;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson.JSON;
import com.hc.commons.core.exceptions.BaseException;
import com.hc.commons.dto.emums.ApiResultCode;
import com.hc.commons.dto.response.ApiResult;
import com.hc.commons.security.beans.handlers.CustomAccessDeniedHandler;
import feign.FeignException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author a1234
 * @description
 * @create 2023-12-29 10:43
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;

    /**
     * 访问被拒绝/无权限访问
     * @param request
     * @param response
     * @param e
     * @throws Exception
     */
    @ExceptionHandler(AccessDeniedException.class)
    public void handleException(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws Exception {
        accessDeniedHandler.handle(request, response, e);
    }

    /**
     * Feign 接口调用异常
     * 包括 被调用服务不可用/被限流/404 等
     * @param e
     * @param response
     * @return
     */
    @ExceptionHandler(FeignException.class)
    public ApiResult<?> feignExceptionHandler(FeignException e, HttpServletResponse response) {
        // 直接使用 FeignException 中的默认状态码进行传输
        int status = e.status();
        response.setStatus(status);
        return ApiResult.error(e.getMessage());
    }

    /**
     * sentinel 异常处理
     * 主要处理
     * @param e
     * @param response
     * @return
     */
    @ExceptionHandler(BlockException.class)
    public ApiResult<?> sentinelBlockExceptionHandler(BlockException e, HttpServletResponse response) {
        ApiResultCode code = ApiResultCode.ERROR;
        if (e instanceof FlowException) {
            // 流量控制规则触发（QPS/线程数超限) 429
            code = ApiResultCode.SENTINEL_FLOW_LIMITED;
        } else if (e instanceof ParamFlowException) {
            // 热点参数限流触发 429
            code = ApiResultCode.SENTINEL_HOT_LIMITED;
        } else if (e instanceof DegradeException) {
            // 熔断降级规则触发（慢调用/异常比例）503
            code = ApiResultCode.SENTINEL_DEGRADE_LIMITED;
        } else if (e instanceof SystemBlockException) {
            // 系统保护规则触发（负载/CPU超阈值） 503
            code = ApiResultCode.SERVICE_UNAVAILABLE;
        } else if (e instanceof AuthorityException) {
            // 黑白名单规则触发 403
            code = ApiResultCode.ACCESS_DENIED;
        }

        response.setStatus(code.getResponseStatus());

        return ApiResult.result(code, null);
    }

    /**
     * 业务异常处理
     * 主要为格式化返回结果，统一异常信息
     * @param e
     * @param response
     * @return
     */
    @ExceptionHandler(BaseException.class)
    public ApiResult<?> handleBaseException(BaseException e, HttpServletResponse response) {
        String message = e.getMessage();
        ApiResultCode code = e.getCode();
        if (StringUtils.isEmpty(message)) {
            String msg = code.getMsg();
            if (StringUtils.isEmpty(msg)) {
                message = "系统繁忙，请稍后重试";
            }else {
                message = msg;
            }

        }
        response.setStatus(code.getResponseStatus());
        return ApiResult.result(code.getCode(), message, null);
    }

    /**
     * 兜底处理
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ApiResult<?> handleException(Exception e) {
        LOGGER.error("系统繁忙，请稍后重试", e);
        return ApiResult.error("发生未知异常 -->> " + e.getMessage());
    }

}
