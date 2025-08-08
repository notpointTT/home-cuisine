package com.hc.commons.core.filter;

import com.alibaba.nacos.common.utils.UuidUtils;
import com.hc.commons.core.model.HeaderModifiableRequestWrapper;
import com.hc.commons.dto.constant.CommonConstant;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author a1234
 * @description 链路编号设置
 * @create 2025-08-08 10:57
 */
public class TraceFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String gTraceId = request.getHeader(CommonConstant.Headers.X_G_TRACE_ID_HEADER);
        String traceId = request.getHeader(CommonConstant.Headers.X_TRACE_ID_HEADER);

        HeaderModifiableRequestWrapper requestWrapper = new HeaderModifiableRequestWrapper(request);
        if (StringUtils.isEmpty(gTraceId)) {
            requestWrapper.putHeader(CommonConstant.Headers.X_G_TRACE_ID_HEADER, UuidUtils.generateUuid());
        }
        if (StringUtils.isEmpty(traceId)) {
            requestWrapper.putHeader(CommonConstant.Headers.X_TRACE_ID_HEADER, UuidUtils.generateUuid());
        }

        filterChain.doFilter(requestWrapper, response);
    }
}
