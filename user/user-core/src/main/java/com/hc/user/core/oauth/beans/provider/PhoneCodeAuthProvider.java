package com.hc.user.core.oauth.beans.provider;

import com.hc.user.core.oauth.beans.token.PhoneCodeAuthenticationToken;
import com.hc.user.core.oauth.exceptions.LoginFailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * 手机验证码登录逻辑
 */
@Component
public class PhoneCodeAuthProvider implements AuthenticationProvider {

    @Autowired
    private AbstractPhoneCodeLogin phoneCodeLogin;

    /**
     * 验证手机验证码登录认证
     * @param authentication the authentication request object.
     *
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        PhoneCodeAuthenticationToken token = (PhoneCodeAuthenticationToken) authentication;
        String phone = token.getPrincipal();
        String code = token.getCredentials();
        try {
            phoneCodeLogin.login(phone, code);
        }catch (Exception e) {
//            throw new LoginFailException(e.getMessage());
        }

        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return PhoneCodeAuthenticationToken.class.equals(authentication);
    }
}
