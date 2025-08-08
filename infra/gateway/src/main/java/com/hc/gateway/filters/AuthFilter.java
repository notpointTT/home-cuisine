package com.hc.gateway.filters;

import com.alibaba.fastjson.JSON;
import com.hc.commons.dto.auth.AccessTokenUser;
import com.hc.commons.dto.constant.CommonConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;

public class AuthFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 提取 Header 中的 Token
        ServerHttpRequest request = exchange.getRequest();
        String token = getToken(request);
        if (token != null) {
            if (!validateToken(token)) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            AccessTokenUser accessTokenUser = getTokenUser(token);
            exchange.getRequest().mutate()
                    .header(CommonConstant.Headers.X_USER_ID_HEADER, accessTokenUser.getUsername())
                    .header(CommonConstant.Headers.X_USER_ROLES_HEADER, JSON.toJSONString(accessTokenUser.getRoles()))
                    .build();
        }


        return chain.filter(exchange);
    }


    private String getToken(ServerHttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        List<String> tokenHeaders = headers.get(CommonConstant.Headers.TOKEN_HEADER);
        if (tokenHeaders == null || tokenHeaders.isEmpty()) {
            return null;
        }
        return tokenHeaders.get(0);
    }

    private AccessTokenUser getTokenUser(String token) {
        Claims body = Jwts.parser()
                .setSigningKey(CommonConstant.TokenConstant.SIGN_KEY)
                .parseClaimsJws(token)
                .getBody();
        String jsonUser = body.getSubject();
        return JSON.parseObject(jsonUser, AccessTokenUser.class);
    }


    public boolean validateToken(String token) {
        try {
            // 1. 解析JWT claims（自动验证签名）
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(CommonConstant.TokenConstant.SIGN_KEY)
                    .parseClaimsJws(token);

            // 2. 检查过期时间（exp claim）
            Claims claims = claimsJws.getBody();
            Date expiration = claims.getExpiration();
            if (expiration.before(new Date())) {
                // 令牌已过期
                return false;
            }

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // 捕获以下异常：
            // - SignatureException: 签名不匹配
            // - MalformedJwtException: JWT格式错误
            // - ExpiredJwtException: JWT已过期
            // - UnsupportedJwtException: 不支持的JWT
            return false;
        }
    }

}
