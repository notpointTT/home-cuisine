package com.hc.user.core.oauth.beans;

import com.hc.common.constant.CommonConstant;
import com.hc.user.core.oauth.beans.token.HcLoginAuthToken;
import com.hc.user.core.oauth.beans.token.PhoneCodeAuthenticationToken;
import com.hc.user.core.oauth.exceptions.UnsupportedLoginTypeException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {
    public JwtLoginFilter() {
        super(new AntPathRequestMatcher("/user/auth/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        String loginType = request.getParameter("loginType");
        Authentication authentication = null;
        if (CommonConstant.LoginType.PHONE_CODE.equals(loginType)) {
            String phone = request.getParameter("phone");
            String code = request.getParameter("code");
            authentication = new PhoneCodeAuthenticationToken(phone, code);
        }
        if (authentication == null) {
            throw new UnsupportedLoginTypeException("不支持当前登录认证方式");
        }
        return getAuthenticationManager().authenticate(authentication);
    }




}