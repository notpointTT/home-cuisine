package com.hc.commons.security.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.hc.commons.security.config.GlobalExceptionHandler;
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
@Component
@ConditionalOnClass(WebMvcConfigurer.class)
public class SentinelExceptionHandler implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        // 直接抛出异常，由 GlobalExceptionHandler 统一处理平台内所有异常
        throw e;
    }
}
