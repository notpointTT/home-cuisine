package com.hc.user.core.oauth;

import com.hc.common.constant.CommonConstant;
import com.hc.common.utils.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class JwtTokenConfig {


    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }

    /**
     * Token 存储策略
     * 令牌的存储策略，这里使用的是JwtTokenStore，使用JWT的令牌生成方式，其实还有以下两个比较常用的方式：
     * RedisTokenStore：将令牌存储到Redis中，此种方式相对于内存方式来说性能更好
     * JdbcTokenStore：将令牌存储到数据库中，需要新建从对应的表，有兴趣的可以尝试
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * 在 Jwt token 和 OAuth2 身份信息之间的转换
     * 令牌增强类，用于JWT令牌和OAuth身份进行转换
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        // JWT签名的秘钥，这里使用的是对称加密，资源服务中也要使用相同的秘钥进行校验和解析JWT令牌
        tokenConverter.setSigningKey(CommonConstant.TokenConstant.SIGN_KEY);
        return tokenConverter;
    }

}
