package com.hc.user.core.oauth;

import com.hc.user.core.oauth.beans.JwtAuthFilter;
import com.hc.user.core.oauth.beans.handlers.CustomAccessDeniedHandler;
import com.hc.user.core.oauth.beans.handlers.NeedLoginHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
// 启用方法级别安全控制
@EnableGlobalMethodSecurity(
        // 启用Spring的@PreAuthorize等注解
        prePostEnabled = true,
        // 启用Spring的@Secured注解
        securedEnabled = true,
        // 启用JSR-250标准注解（如@RolesAllowed、@PermitAll）
        jsr250Enabled = true
)
public class JwtAuthConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtLoginConfig loginConfig;

    @Autowired
    private JwtAuthFilter authFilter;

    @Autowired
    private NeedLoginHandler needLoginHandler;
    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http
                .csrf()
                .disable()
                // 禁用表单登录
                .formLogin().disable()
                // 不会写入Cookie JSESSIONID
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .apply(loginConfig)
                .and()
                .authorizeRequests()
                // 过滤登录等需要放开的请求
                .antMatchers("/auth/**", "/open/**").permitAll()
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
