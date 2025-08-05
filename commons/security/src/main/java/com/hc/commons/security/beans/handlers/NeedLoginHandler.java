package com.hc.commons.security.beans.handlers;

import com.alibaba.fastjson.JSON;
import com.hc.commons.dto.response.ApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class NeedLoginHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        // 返回 401
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(
                JSON.toJSONString(ApiResult.error("需登录后访问"))
        );
    }
}
