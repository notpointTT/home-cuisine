package com.hc.external.sms;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(SmsConfig.class)
public class SmsAutoConfig {

    @Bean
    @ConditionalOnBean(SmsConfig.class)
    public SmsSender smsSender() {
        return new SmsSender();
    }

    @Bean
    @ConditionalOnBean(SmsSender.class)
    public VerificationCodeHandler verificationCodeHandler() {
        return new VerificationCodeHandler();
    }

}
