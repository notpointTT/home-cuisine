package com.hc.auth.user.core.auth.handlers;

import com.alibaba.fastjson.JSON;
import com.hc.commons.dto.auth.AccessTokenUser;
import com.hc.commons.dto.response.ApiResult;
import com.hc.commons.security.model.AuthUserInfo;
import com.hc.commons.security.UserAuthCache;
import com.hc.commons.security.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    /**
     * accessToken 过期时间 30分钟
     */
    private static final long ACCESS_TOKEN_TIME_OUT_SECOND = 30 * 60;
    /**
     * refreshToken 过期时间 12小时
     */
    private static final long REFRESH_TOKEN_TIME_OUT_SECOND = 12 * 60 * 60;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserAuthCache userAuthCache;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException, ServletException {

        // 生成 token 返回前端
        AuthUserInfo details = (AuthUserInfo) authentication.getDetails();
        String username = details.getUsername();
        List<String> roles = details.getRoles();
        AccessTokenUser accessTokenUser = new AccessTokenUser();
        accessTokenUser.setUsername(username);
        accessTokenUser.setRoles(roles);


        String jsonUser = JSON.toJSONString(accessTokenUser);
        String accessToken = jwtUtil.createToken(jsonUser, ACCESS_TOKEN_TIME_OUT_SECOND);
        String refreshToken = jwtUtil.createToken(jsonUser, REFRESH_TOKEN_TIME_OUT_SECOND);


        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("accessToken", accessToken);
        tokenMap.put("refreshToken", refreshToken);

        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(JSON.toJSONString(ApiResult.success(tokenMap)));

    }
}
