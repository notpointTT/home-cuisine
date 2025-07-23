package com.hc.user.core.oauth.beans;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义 Provider 管理器
 * Provider 为登录逻辑的执行代码
 * 包括各种不同的登录模式 - SMS、UP、WX、QR
 */
@Component
public class JwtProviderManager extends ProviderManager {
    public JwtProviderManager(List<AuthenticationProvider> providers) {
        super(providers);
    }
}
