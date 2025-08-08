package com.hc.commons.core.config;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.hc.commons.core.exceptions.BaseException;
import com.hc.commons.dto.constant.CommonConstant;
import com.hc.commons.dto.emums.ApiResultCode;
import com.hc.commons.dto.response.ApiResult;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author a1234
 * @description
 * @create 2023-12-29 10:43
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常处理
     * 主要为格式化返回结果，统一异常信息
     * @param e
     * @param response
     * @return
     */
    @ExceptionHandler(BaseException.class)
    public ApiResult<?> handleBaseException(BaseException e, HttpServletRequest request, HttpServletResponse response) {
        String message = e.getMessage();
        if (StringUtils.isEmpty(message)) {
            message = "系统繁忙，请稍后重试";
        }

        log.error(message, e);

        request.setAttribute(CommonConstant.Headers.X_EXCEPTION, e);

        response.setStatus(e.getResponseStatus());
        return ApiResult.error(message);
    }

    /**
     * 兜底处理
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ApiResult<?> handleException(Exception e, HttpServletRequest request, HttpServletResponse response) {
        log.error("系统繁忙，请稍后重试", e);

        request.setAttribute(CommonConstant.Headers.X_EXCEPTION, e);

        response.setStatus(500);
        return ApiResult.error("系统繁忙，请稍后重试");
    }

}
