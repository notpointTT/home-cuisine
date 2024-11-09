package com.hc.user.core.oauth.beans.token;

import com.hc.common.constant.CommonConstant;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.Date;

import static javafx.print.Paper.C;

@Component
public class JwtUtil {

    public String createToken(String sign, Long expireSeconds) {

        return Jwts.builder()
                .setSubject(sign)
                .signWith(SignatureAlgorithm.HS256, CommonConstant.TokenConstant.SIGN_KEY)
                .setExpiration(new Date(new Date().getTime() + 1000 * expireSeconds))
                .compact();
    }

}
