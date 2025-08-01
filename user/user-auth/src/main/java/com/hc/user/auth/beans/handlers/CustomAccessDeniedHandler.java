package com.hc.user.auth.beans.handlers;

import com.alibaba.fastjson.JSON;
import com.hc.common.model.ApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Deprecated
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        // 返回 401
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.getWriter().write(
                JSON.toJSONString(ApiResult.error("无权限访问"))
        );
    }
}
