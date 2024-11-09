package com.hc.user.core.oauth.beans;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtAuthManager extends ProviderManager {
    public JwtAuthManager(List<AuthenticationProvider> providers) {
        super(providers);
    }
}
