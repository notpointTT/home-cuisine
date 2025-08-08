package com.hc.commons.security.config;

import com.hc.commons.security.AbstractCustomAuthConfig;
import com.hc.commons.security.beans.JwtAuthFilter;
import com.hc.commons.security.filter.LogFilter;
import com.hc.commons.security.filter.TraceFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * @author a1234
 * @description
 * @create 2025-08-08 10:55
 */
@Configuration
public class WebFiltersConfig implements AbstractCustomAuthConfig {
    @Autowired
    private TraceFilter traceFilter;
    @Autowired
    private LogFilter logFilter;
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(traceFilter, JwtAuthFilter.class);
        http.addFilterBefore(logFilter, JwtAuthFilter.class);
    }
}
