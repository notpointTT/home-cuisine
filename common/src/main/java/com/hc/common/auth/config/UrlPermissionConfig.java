package com.hc.common.auth.config;

import com.hc.common.auth.AuthRequestInterceptor;
import org.springframework.context.annotation.Bean;

public class UrlPermissionConfig {

    @Bean
    public AuthRequestInterceptor authRequestInterceptor() {
        return new AuthRequestInterceptor();
    }

}
