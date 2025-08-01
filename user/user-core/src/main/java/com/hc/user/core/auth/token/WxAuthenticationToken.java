package com.hc.user.core.auth.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.ArrayList;

/**
 * 微信认证
 * 20250721 - TODO
 */
public class WxAuthenticationToken extends AbstractAuthenticationToken {

    private final String code;

    public WxAuthenticationToken(String code) {
        super(new ArrayList<>());
        this.code = code;
    }

    /**
     * 令牌
     * @return
     */
    @Override
    public Object getCredentials() {
        return "";
    }

    /**
     * 账户
     * @return
     */
    @Override
    public String getPrincipal() {
        return code;
    }
}
