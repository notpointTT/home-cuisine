package com.hc.gateway.filters;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class FilterConfig {

    @Bean
    @Order(0)
    public AuthFilter authFilter() {
        return new AuthFilter();
    }

    @Bean
    @Order(1)
    public TraceFilter traceFilter() {
        return new TraceFilter();
    }

//    public RateLimitFilter rateLimitFilter() {
//        return new RateLimitFilter();
//    }

}
