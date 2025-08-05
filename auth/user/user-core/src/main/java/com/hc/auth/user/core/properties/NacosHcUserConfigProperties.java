package com.hc.auth.user.core.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * nacos 配置中心的配置进行集中放置
 */
@Data
@Component
@RefreshScope
@ConfigurationProperties(prefix = "hc.user")
public class NacosHcUserConfigProperties {

    private AuthProperties auth = new AuthProperties();

    @Data
    public class AuthProperties {
        private Long accessTokenExpireSeconds;
        private Long refreshTokenExpireSeconds;
    }

}
