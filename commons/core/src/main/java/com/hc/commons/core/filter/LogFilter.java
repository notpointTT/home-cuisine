package com.hc.commons.core.filter;

import com.hc.commons.core.log.LogEvent;
import com.hc.commons.dto.constant.CommonConstant;
import com.hc.commons.dto.model.LogInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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
public class LogFilter extends OncePerRequestFilter {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String gTraceId = request.getHeader(CommonConstant.Headers.X_G_TRACE_ID_HEADER);
        String traceId = request.getHeader(CommonConstant.Headers.X_TRACE_ID_HEADER);
        String requestFrom = request.getHeader(CommonConstant.Headers.X_REQUEST_FROM_HEADER);

        log.info("START -->> X_REQUEST_FROM -> {} || X_G_TRACE_ID -> {} || X_TRACE_ID -> {}", requestFrom, gTraceId, traceId);

        try {
            filterChain.doFilter(request, response);
        } finally {
            log.info("END -->> X_REQUEST_FROM -> {} || X_G_TRACE_ID -> {} || X_TRACE_ID -> {}", requestFrom, gTraceId, traceId);
            log(request, response);
        }

    }

    void log(HttpServletRequest request, HttpServletResponse response) {
        String gTraceId = request.getHeader(CommonConstant.Headers.X_G_TRACE_ID_HEADER);
        String traceId = request.getHeader(CommonConstant.Headers.X_TRACE_ID_HEADER);

        Object o = request.getAttribute(CommonConstant.Headers.X_EXCEPTION);
        LogInfo logInfo = new LogInfo();
        logInfo.setGlobalTraceId(gTraceId);
        logInfo.setTraceId(traceId);

        logInfo.setRequestUri(request.getRequestURI());
        logInfo.setMethod(request.getMethod());

        if (o instanceof Exception) {
            // 发生异常
            logInfo.setErrorMsg(((Exception) o).getMessage());
        }
        logInfo.setStatus(response.getStatus());

        eventPublisher.publishEvent(new LogEvent(logInfo));
    }
}
