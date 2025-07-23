package com.hc.user.core.oauth.beans.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 用户密码认证
 * UsernamePassword - UP
 */
public class UPLoginAuthToken extends AbstractAuthenticationToken {

    private String principal;
    private String credentials;
    private String loginType;

    public UPLoginAuthToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    public UPLoginAuthToken(String principal, String credentials, String loginType) {
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
