package com.hc.user.core.oauth.beans.handlers;

import com.alibaba.fastjson.JSON;
import com.hc.common.model.ApiResult;
import com.hc.user.core.oauth.beans.token.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtUtil jwtUtil;
    
    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException, ServletException {

        // 生成 token 返回前端
        System.out.println(authentication);

        // accessToken 过期时间 30分钟
        String accessToken = jwtUtil.createToken(authentication.getPrincipal().toString(), 60 * 30L);
        String refreshToken = jwtUtil.createToken(accessToken, 6 * 60 * 60L);

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("accessToken", accessToken);
        tokenMap.put("refreshToken", refreshToken);
        response.setContentType("application/json");
        response.getWriter().write(JSON.toJSONString(ApiResult.success(tokenMap)));

    }
}
