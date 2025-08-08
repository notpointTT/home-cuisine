package com.hc.commons.core.config;

import com.hc.commons.core.feign.CustomErrorDecoder;
import com.hc.commons.core.feign.FeignRequestInterceptor;
import feign.Request;
import feign.Retryer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("com.hc")
public class FeignConfig {

    /**
     * 超时配置
     * @return
     */
    @Bean
    public Request.Options options() {
        return new Request.Options(
                30000, // 连接超时时间(ms)
                30000   // 读取超时时间(ms)
        );
    }

    /**
     * 重试配置
     * @return
     */
    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(100, 1000, 3);
    }

    @Bean
    public FeignRequestInterceptor feignRequestInterceptor() {
        return new FeignRequestInterceptor();
    }

    @Bean
    public CustomErrorDecoder customErrorDecoder() {
        return new CustomErrorDecoder();
    }

}
