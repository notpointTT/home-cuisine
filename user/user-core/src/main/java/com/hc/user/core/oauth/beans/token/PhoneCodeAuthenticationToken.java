package com.hc.user.core.oauth.beans.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

public class PhoneCodeAuthenticationToken extends AbstractAuthenticationToken {

    private final String phone;
    private final String code;

    public PhoneCodeAuthenticationToken(String phone, String code) {
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
