package com.hc.user.core.oauth.beans;

import com.hc.common.exceptions.UserNotLoginException;
import com.hc.user.core.model.auth.AuthUserInfo;
import com.hc.user.core.oauth.UserTokenCache;
import com.hc.common.utils.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author a1234
 * @description 权限过滤器，针对每个请求，判断是否携带token，且具有当前资源权限
 * @create 2025-07-23 18:27
 */
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil;
    private UserTokenCache userTokenCache;

    public JwtAuthFilter(JwtUtil jwtUtil, UserTokenCache userTokenCache) {
        this.jwtUtil = jwtUtil;
        this.userTokenCache = userTokenCache;
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
        // 1. 从JWT中提取用户名
        String username = jwtUtil.parseToken(token);

        // 2. 加载用户信息
        // 从JWT自定义声明中直接读取权限（推荐无状态方案）
        UserDetails userDetails = getTokenUser(token);

        return new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                // credentials置空
                null,
                userDetails.getAuthorities()
        );
    }

    /**
     * 从缓存中获取用户权限信息
     */
    private UserDetails getTokenUser(String token) {
        // 从缓存中获取
        AuthUserInfo authUserInfo = userTokenCache.tokenUser(token);
        if (authUserInfo == null) {
            throw new UserNotLoginException();
        }

        return authUserInfo;
    }


}
