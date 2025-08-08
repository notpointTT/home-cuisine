package com.hc.commons.core.config;

import com.hc.commons.core.filter.LogFilter;
import com.hc.commons.core.filter.TraceFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @author a1234
 * @description
 * @create 2025-08-08 10:55
 */
@Configuration
public class WebFiltersConfig {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public TraceFilter traceFilter() {
        return new TraceFilter();
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE+1)
    public LogFilter logFilter() {
        return new LogFilter();
    }
}
