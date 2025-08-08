package com.hc.commons.core.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.hc.commons.core.config.GlobalExceptionHandler;
import com.hc.commons.core.exceptions.ResourceDeniedException;
import com.hc.commons.core.exceptions.ServiceUnavailableException;
import com.hc.commons.core.exceptions.TooManyRequestException;
import com.hc.commons.dto.emums.ApiResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Sentinel 限流降级全局异常处理
 * 此部分只做 Sentinel 的异常接收和抛出，不做具体处理
 * 实际处理 @see GlobalExceptionHandler
 * @author a1234
 * @see GlobalExceptionHandler
 */
@Slf4j
@Component
@ConditionalOnClass(WebMvcConfigurer.class)
public class SentinelExceptionHandler implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        log.warn("Sentinel规则触发: {} {}", request.getRequestURI(), e.getMessage());

        // 直接抛出异常，由 GlobalExceptionHandler 统一处理平台内所有异常
        if (e instanceof FlowException) {
            // 流量控制规则触发（QPS/线程数超限) 429
            throw new TooManyRequestException();
        } else if (e instanceof ParamFlowException) {
            // 热点参数限流触发 429
            throw new TooManyRequestException();
        } else if (e instanceof DegradeException) {
            // 熔断降级规则触发（慢调用/异常比例）503
            throw new ServiceUnavailableException();
        } else if (e instanceof SystemBlockException) {
            // 系统保护规则触发（负载/CPU超阈值） 503
            throw new ServiceUnavailableException();
        } else if (e instanceof AuthorityException) {
            // 黑白名单规则触发 403
            throw new ResourceDeniedException();
        }
        throw new ServiceUnavailableException();
    }
}
