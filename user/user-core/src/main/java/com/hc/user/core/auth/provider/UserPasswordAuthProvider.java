package com.hc.user.core.auth.provider;

import com.hc.user.auth.model.AuthUserInfo;
import com.hc.user.core.auth.token.UserPasswordLoginAuthToken;
import com.hc.user.auth.exceptions.LoginFailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class UserPasswordAuthProvider implements AuthenticationProvider {

    @Autowired
    private AbstractLogin abstractLogin;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserPasswordLoginAuthToken token = (UserPasswordLoginAuthToken) authentication;
        // password 暂明文传输至后台
        String password = authentication.getCredentials().toString();
        String username = authentication.getPrincipal().toString();

        try {
            AuthUserInfo authUserInfo = abstractLogin.userPasswordLogin(username, password);
            token.setDetails(authentication);
        } catch (AuthenticationException authenticationException) {
            throw authenticationException;
        } catch (Exception e) {
            throw new LoginFailException();
        }

        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UserPasswordLoginAuthToken.class.equals(authentication);
    }
}
