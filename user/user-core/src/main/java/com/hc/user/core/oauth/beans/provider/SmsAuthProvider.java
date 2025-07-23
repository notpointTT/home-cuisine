package com.hc.user.core.oauth.beans.provider;

import com.hc.common.utils.JwtUtil;
import com.hc.user.core.oauth.beans.token.SmsAuthenticationToken;
import com.hc.user.core.oauth.exceptions.LoginFailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * 手机验证码登录逻辑
 */
@Component
public class SmsAuthProvider implements AuthenticationProvider {

    @Autowired
    private AbstractLogin phoneCodeLogin;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 验证手机验证码登录认证
     *
     * @param authentication the authentication request object.
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsAuthenticationToken token = (SmsAuthenticationToken) authentication;
        String phone = token.getPrincipal();
        String code = token.getCredentials();
        try {
            UserDetails userDetails = phoneCodeLogin.smsLogin(phone, code);
            token.setDetails(userDetails);
        } catch (AuthenticationException authenticationException) {
            throw authenticationException;
        } catch (Exception e) {
            throw new LoginFailException();
        }
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsAuthenticationToken.class.equals(authentication);
    }
}
