package com.hc.gateway.filters;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.common.utils.UuidUtils;
import com.hc.commons.dto.constant.CommonConstant;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author a1234
 * @description 在 Header 中记录 链路ID
 * @create 2025-08-08 10:42
 */
public class TraceFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        HttpHeaders headers = exchange.getRequest().getHeaders();
        String gTraceId = getHeader(exchange.getRequest(), CommonConstant.Headers.X_G_TRACE_ID_HEADER);
        if (StringUtils.isEmpty(gTraceId)) {
            gTraceId = UuidUtils.generateUuid();
        }

        exchange.getRequest().mutate()
                .header(CommonConstant.Headers.X_G_TRACE_ID_HEADER, gTraceId)
                .build();

        return chain.filter(exchange);
    }

    private String getHeader(ServerHttpRequest request, String headerName) {
        HttpHeaders headers = request.getHeaders();
        List<String> tokenHeaders = headers.get(headerName);
        if (tokenHeaders == null || tokenHeaders.isEmpty()) {
            return null;
        }
        return tokenHeaders.get(0);
    }
}
