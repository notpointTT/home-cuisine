package com.hc.gateway.filters;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public AuthFilter authFilter() {
        return new AuthFilter();
    }

//    public RateLimitFilter rateLimitFilter() {
//        return new RateLimitFilter();
//    }

}
