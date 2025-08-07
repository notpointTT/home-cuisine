package com.hc.commons.security.beans;

import com.hc.commons.security.AbstractCustomAuthConfig;
import com.hc.commons.security.beans.handlers.CustomAccessDeniedHandler;
import com.hc.commons.security.beans.handlers.NeedLoginHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
// 通用配置，优先级最低
@Order(Integer.MAX_VALUE)
public class CommonAuthConfig implements AbstractCustomAuthConfig {

    @Autowired
    private JwtAuthFilter authFilter;

    @Autowired
    private NeedLoginHandler needLoginHandler;
    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                // 禁用表单登录
                .formLogin().disable()
                // 不会写入Cookie JSESSIONID
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 过滤登录等需要放开的请求
                .antMatchers("/auth/**", "/open/**").permitAll()
                .antMatchers("/info/base/admin2").hasRole("ADMIN")
                // 其余请求需要登录
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                //定义异常处理器
                .exceptionHandling()
                // 未登录处理
                .authenticationEntryPoint(needLoginHandler)
                // 无权限处理
                .accessDeniedHandler(customAccessDeniedHandler);
    }

}
