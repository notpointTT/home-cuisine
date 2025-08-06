package com.hc.commons.security.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.fastjson.JSON;
import com.hc.commons.dto.emums.ApiResultCode;
import com.hc.commons.dto.response.ApiResult;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Sentinel 限流降级全局异常处理
 */
@Component
@ConditionalOnClass(WebMvcConfigurer.class)
public class SentinelExceptionHandler implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {

        ApiResultCode code = ApiResultCode.ERROR;
        if (e instanceof FlowException) {
            code = ApiResultCode.SENTINEL_FLOW_LIMITED;
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
        } else if (e instanceof ParamFlowException) {
            code = ApiResultCode.SENTINEL_HOT_LIMITED;
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
        } else if (e instanceof DegradeException) {
            code = ApiResultCode.SENTINEL_DEGRADE_LIMITED;
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
        } else if (e instanceof AuthorityException) {
            code = ApiResultCode.ACCESS_DENIED;
            response.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
        }

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(ApiResult.result(code, null)));

    }
}
