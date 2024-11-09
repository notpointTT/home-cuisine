package com.hc.user.core.oauth.beans.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.ArrayList;

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
