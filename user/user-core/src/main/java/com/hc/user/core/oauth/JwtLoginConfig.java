package com.hc.user.core.oauth;

import com.hc.user.core.oauth.beans.JwtLoginFilter;
import com.hc.user.core.oauth.beans.handlers.LoginFailHandler;
import com.hc.user.core.oauth.beans.handlers.LoginSuccessHandler;
import com.hc.user.core.oauth.beans.provider.SmsAuthProvider;
import com.hc.user.core.oauth.beans.provider.UsernamePasswordAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Jwt 认证配置
 */
@Configuration
public class JwtLoginConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private LoginSuccessHandler successHandler;
    @Autowired
    private LoginFailHandler failHandler;

    @Autowired
    private UsernamePasswordAuthProvider usernamePasswordAuthProvider;
    @Autowired
    private SmsAuthProvider smsAuthProvider;

    @Autowired
    private AuthenticationManager authenticationManager;


    /**
     * 将登录接口的过滤器配置到过滤器链中
     * 1. 配置登录成功、失败处理器
     * 2. 配置自定义的userDetailService（从数据库中获取用户数据）
     * 3. 将自定义的过滤器配置到spring security的过滤器链中，配置在UsernamePasswordAuthenticationFilter之前
     * @param http
     */
    @Override
    public void configure(HttpSecurity http) {
        JwtLoginFilter filter = new JwtLoginFilter();
        // authenticationManager 中已经预设 hc系统内的 provider
        filter.setAuthenticationManager(authenticationManager);
        //认证成功处理器
        filter.setAuthenticationSuccessHandler(successHandler);
        //认证失败处理器
        filter.setAuthenticationFailureHandler(failHandler);

//        http.authenticationProvider(smsAuthProvider);
        //将这个过滤器添加到UsernamePasswordAuthenticationFilter之前执行
        http.addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class);
    }
}
