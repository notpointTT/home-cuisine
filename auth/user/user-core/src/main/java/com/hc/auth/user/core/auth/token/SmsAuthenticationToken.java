package com.hc.auth.user.core.auth.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.ArrayList;

/**
 * 短信认证模式
 */
public class SmsAuthenticationToken extends AbstractAuthenticationToken {

    private final String phone;
    private final String code;

    public SmsAuthenticationToken(String phone, String code) {
        super(new ArrayList<>());
        this.phone = phone;
        this.code = code;
    }

    @Override
    public String getCredentials() {
        return code;
    }

    @Override
    public String getPrincipal() {
        return phone;
    }


}
