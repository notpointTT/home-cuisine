package com.hc.business.order.config;

import com.hc.commons.security.AbstractCustomAuthConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@Order(0)
public class OrderAuthConfig implements AbstractCustomAuthConfig {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // 过滤登录等需要放开的请求
                .antMatchers("/openx/**").permitAll();
    }
}
