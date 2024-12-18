package com.hc.common.web;

import com.hc.common.auth.AuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebCommonConfig implements WebMvcConfigurer {

    @Autowired(required = false)
    private AuthRequestInterceptor authRequestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (authRequestInterceptor != null) {
            registry.addInterceptor(authRequestInterceptor).addPathPatterns("/**");
        }

    }
}
