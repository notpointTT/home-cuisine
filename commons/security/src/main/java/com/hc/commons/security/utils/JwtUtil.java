package com.hc.commons.security.utils;

import com.hc.commons.dto.constant.CommonConstant;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    /**
     * 根据用户标识 sign 返回 token
     * @param sign 用户标识
     * @param expireSeconds token 过期时间
     * @return
     */
    public String createToken(String sign, Long expireSeconds) {

        return Jwts.builder()
                .setSubject(sign)
                .signWith(SignatureAlgorithm.HS256, CommonConstant.TokenConstant.SIGN_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * expireSeconds))
                .compact();
    }

    /**
     * 根据 token 获取 sign （用户标识）
     * @param token
     * @return sign （用户标识）
     */
    public String parseToken(String token) {
        Claims body = Jwts.parser()
                .setSigningKey(CommonConstant.TokenConstant.SIGN_KEY)
                .parseClaimsJws(token)
                .getBody();
        return body.getSubject();
    }

    /**
     * 验证JWT令牌有效性
     * @param token JWT令牌
     * @return true=有效，false=无效
     */
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
