package com.hc.common.sms;

import com.hc.common.utils.DesUtil;
import org.bouncycastle.jcajce.provider.symmetric.DES;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@RefreshScope
@ConditionalOnProperty("aliyun.sms.accessKeyId")
public class SmsConfig {

    /**
     * 阿里云账号的accessKeyId
     */
    @Value("${aliyun.sms.accessKeyId}")
    private String accessKeyId;
    /**
     * 阿里云账号的accessKeySecret
     */
    @Value("${aliyun.sms.accessKeySecret}")
    private String accessKeySecret;
    /**
     * 短信服务访问的域名
     */
    @Value("${aliyun.sms.endpoint}")
    private String endpoint;

    public com.aliyun.dysmsapi20170525.Client createClient() throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                // 您的 AccessKey ID
                .setAccessKeyId(DesUtil.desDecrypt(accessKeyId, DesUtil.key))
                // 您的 AccessKey Secret
                .setAccessKeySecret(DesUtil.desDecrypt(accessKeySecret, DesUtil.key));
        // 访问的域名
        config.endpoint = endpoint;
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

}
