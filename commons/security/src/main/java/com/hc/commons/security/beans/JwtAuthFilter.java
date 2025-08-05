package com.hc.commons.security.beans;

import com.hc.commons.security.beans.token.CacheableUserAuthenticationToken;
import com.hc.commons.security.model.AuthUserInfo;
import com.hc.commons.security.UserAuthCache;
import com.hc.commons.security.utils.JwtUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * @author a1234
 * @description 权限过滤器，针对每个请求，判断是否携带token，且具有当前资源权限
 * @create 2025-07-23 18:27
 */
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil;
    private UserAuthCache userAuthCache;

    public JwtAuthFilter(JwtUtil jwtUtil, UserAuthCache userAuthCache) {
        this.jwtUtil = jwtUtil;
        this.userAuthCache = userAuthCache;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        // 1. 从请求头提取Token
        String token = getToken(request);

        if (token != null && jwtUtil.validateToken(token)) {
            // 2. 构建认证对象
            Authentication auth = buildAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        // 3. 继续过滤器链
        chain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }

    /**
     * 根据JWT构建Authentication对象
     * @param token 有效的JWT令牌
     * @return 已认证的Authentication对象
     */
    public Authentication buildAuthentication(String token) {

        // 从JWT自定义声明中直接读取权限（推荐无状态方案）
        UserDetails userDetails = getTokenUser(token);

        return new CacheableUserAuthenticationToken(userDetails, userAuthCache);
    }

    /**
     * 从缓存中获取用户权限信息
     */
    private UserDetails getTokenUser(String token) {
        String username = jwtUtil.parseToken(token);
        // 直接返回简单 AuthUserInfo
        AuthUserInfo authUserInfo = new AuthUserInfo();
        authUserInfo.setUsername(username);
        authUserInfo.setRoles(Collections.emptyList());

        return authUserInfo;
    }
}
