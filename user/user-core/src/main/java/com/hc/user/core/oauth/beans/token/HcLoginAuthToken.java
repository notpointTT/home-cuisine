package com.hc.user.core.oauth.beans.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

public class HcLoginAuthToken extends AbstractAuthenticationToken {

    private String principal;
    private String credentials;
    private String loginType;

    public HcLoginAuthToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    public HcLoginAuthToken(String principal, String credentials, String loginType) {
        super(new ArrayList<>());
        this.principal = principal;
        this.credentials = credentials;
        this.loginType = loginType;
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    public String getLoginType() {
        return loginType;
    }
}
