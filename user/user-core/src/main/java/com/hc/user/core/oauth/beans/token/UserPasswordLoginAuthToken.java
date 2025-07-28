package com.hc.user.core.oauth.beans.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 用户密码认证
 * UsernamePassword - USER_PASSWORD
 */
public class UserPasswordLoginAuthToken extends AbstractAuthenticationToken {

    private String username;
    private String password;

    public UserPasswordLoginAuthToken(String username, String password) {
        super(new ArrayList<>());
        this.username = username;
        this.password = password;
    }

    @Override
    public Object getCredentials() {
        return password;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }
}
