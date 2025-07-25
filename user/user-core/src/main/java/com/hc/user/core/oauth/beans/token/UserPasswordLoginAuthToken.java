package com.hc.user.core.oauth.beans.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 用户密码认证
 * UsernamePassword - UP
 */
public class UserPasswordLoginAuthToken extends AbstractAuthenticationToken {

    private String principal;
    private String credentials;
    private String loginType;

    public UserPasswordLoginAuthToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    public UserPasswordLoginAuthToken(String principal, String credentials, String loginType) {
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
