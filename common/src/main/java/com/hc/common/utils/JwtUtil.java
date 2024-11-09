package com.hc.common.utils;

import com.hc.common.constant.CommonConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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
                .setExpiration(new Date(new Date().getTime() + 1000 * expireSeconds))
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

}
