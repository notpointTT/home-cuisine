package com.hc.commons.security.beans.handlers;

import com.alibaba.fastjson.JSON;
import com.hc.commons.dto.response.ApiResult;
import com.hc.commons.security.exceptions.AuthException;
import com.hc.commons.security.exceptions.LoginFailException;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginFailHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception)
            throws IOException, ServletException {
        if (!(exception instanceof AuthException)) {
            exception = new LoginFailException();
        }
        AuthException e = (AuthException) exception;

        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(JSON.toJSONString(ApiResult.result(e.getResultCode(), null)));
    }
}
