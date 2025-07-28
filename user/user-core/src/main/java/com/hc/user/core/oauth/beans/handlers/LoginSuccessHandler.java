package com.hc.user.core.oauth.beans.handlers;

import com.alibaba.fastjson.JSON;
import com.hc.common.model.ApiResult;
import com.hc.common.utils.JwtUtil;
import com.hc.user.core.model.auth.AuthUserInfo;
import com.hc.user.core.oauth.UserAuthCache;
import com.hc.user.core.properties.NacosHcUserConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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

        String accessToken = jwtUtil.createToken(username, ACCESS_TOKEN_TIME_OUT_SECOND);
        String refreshToken = jwtUtil.createToken(username, REFRESH_TOKEN_TIME_OUT_SECOND);

        // 设置 UserAuth 也就是当前登录人信息
        userAuthCache.setUserAuth(username, details);

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("accessToken", accessToken);
        tokenMap.put("refreshToken", refreshToken);

        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(JSON.toJSONString(ApiResult.success(tokenMap)));

    }
}
