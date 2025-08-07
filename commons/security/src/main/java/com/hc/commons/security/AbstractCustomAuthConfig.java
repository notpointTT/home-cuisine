package com.hc.commons.security;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;

public interface AbstractCustomAuthConfig {

    void configure(HttpSecurity http) throws Exception;

}
