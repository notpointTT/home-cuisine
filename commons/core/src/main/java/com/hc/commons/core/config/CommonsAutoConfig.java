package com.hc.commons.core.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author a1234
 * @description
 * @create 2023-12-10 17:37
 */
@Configuration
@Import({
        FeignConfig.class,
        WebFiltersConfig.class
})
@ComponentScan("com.hc.commons")
public class CommonsAutoConfig {

}
