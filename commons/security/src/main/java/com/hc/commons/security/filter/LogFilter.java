package com.hc.commons.security.filter;

import com.hc.commons.dto.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author a1234
 * @description
 * @create 2025-08-08 15:12
 */
@Slf4j
@Component
public class LogFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String gTraceId = request.getHeader(CommonConstant.Headers.X_G_TRACE_ID_HEADER);
        String traceId = request.getHeader(CommonConstant.Headers.X_TRACE_ID_HEADER);
        String requestFrom = request.getHeader(CommonConstant.Headers.X_REQUEST_FROM_HEADER);

        log.info("X_REQUEST_FROM -> {} || X_G_TRACE_ID -> {} || X_TRACE_ID -> {}", requestFrom, gTraceId, traceId);

        try {
            filterChain.doFilter(request, response);
        }catch (Exception e) {

            log.error("发生异常 ->> ", e);
            throw e;
        }

    }
}
