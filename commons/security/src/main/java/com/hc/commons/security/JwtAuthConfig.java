package com.hc.commons.security;

import com.hc.commons.security.beans.JwtAuthFilter;
import com.hc.commons.security.beans.handlers.CustomAccessDeniedHandler;
import com.hc.commons.security.beans.handlers.NeedLoginHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Slf4j
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

    @Autowired(required = false)
    private List<AbstractCustomAuthConfig> customAuthConfigs;


//    @Autowired
//    private RoleAccessDecisionVoter roleAccessDecisionVoter;

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 应用登录配置
        if (customAuthConfigs != null && !customAuthConfigs.isEmpty()) {
           for (AbstractCustomAuthConfig customAuthConfig: customAuthConfigs) {
               try {
                   customAuthConfig.configure(http);
               }catch (Exception e) {
                   log.error("自定义权限配置应用异常", e);
               }
           }
        }
    }


}
