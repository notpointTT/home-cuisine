package com.hc.user.core.oauth.beans.provider;

import com.hc.user.core.oauth.beans.token.WxAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * 用于微信登录
 */
@Component
public class WxAuthProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // todo 调用微信认证接口

        WxAuthenticationToken token = (WxAuthenticationToken) authentication;
        String code = token.getPrincipal();
        // todo 登录凭证校验接口，appId+appSecret+code
        // todo 校验成功，返回 session_key+openId

        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return WxAuthenticationToken.class.equals(authentication);
    }
}
