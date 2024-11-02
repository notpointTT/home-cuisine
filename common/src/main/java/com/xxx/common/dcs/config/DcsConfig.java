package com.xxx.common.dcs.config;

import com.xxx.common.dcs.limit.RateLimitDbConfigurator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class DcsConfig {

    @Bean
    @ConditionalOnMissingBean
    public RateLimitDbConfigurator rateLimitDbConfigurator() {
        return new RateLimitDbConfigurator() {
            @Override
            public int getQps(String appId, String serverInstance, String url) {
                return 100;
            }
        };
    }

    @Bean
    public DcsRequestHandlerInterceptor dcsRequestHandlerInterceptor() {
        return new DcsRequestHandlerInterceptor();
    }
}
