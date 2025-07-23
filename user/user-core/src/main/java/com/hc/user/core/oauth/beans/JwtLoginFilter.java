package com.hc.user.core.oauth.beans;

import com.hc.common.constant.CommonConstant;
import com.hc.user.core.oauth.beans.token.SmsAuthenticationToken;
import com.hc.user.core.oauth.beans.token.WxAuthenticationToken;
import com.hc.user.core.oauth.exceptions.UnsupportedLoginTypeException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * LoginFilter
 * 定义和过滤登录请求
 * attemptAuthentication 是在 AntPathRequestMatcher("/auth/login", "POST") 过滤到后执行
 *
 */
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {
    public JwtLoginFilter() {
        // 设置当前 Filter ，也就是登录动作
        super(new AntPathRequestMatcher("/auth/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        // 规定前端传递登录模式
        String loginType = request.getParameter("loginType");
        Authentication authentication = null;
        // 判断前端使用的登录模式
        if (CommonConstant.LoginType.SMS.equals(loginType)) {
            // 手机短信
            String phone = request.getParameter("phone");
            String code = request.getParameter("code");
            authentication = new SmsAuthenticationToken(phone, code);
        }
        if (CommonConstant.LoginType.WX.equals(loginType)) {
            String code = request.getParameter("code");
            authentication = new WxAuthenticationToken(code);
        }

        if (authentication == null) {
            throw new UnsupportedLoginTypeException();
        }
        return getAuthenticationManager().authenticate(authentication);
    }

}
