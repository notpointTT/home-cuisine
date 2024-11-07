package com.hc.user.core.properties;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * nacos 配置中心的配置进行集中放置
 */
@Component
@RefreshScope
public class NacosHcUserConfigProperties {
}
