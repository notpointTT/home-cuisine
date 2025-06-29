package com.hc.common.sms;

import com.alibaba.fastjson.JSON;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.dysmsapi20170525.models.SendSmsResponseBody;
import com.aliyun.teautil.models.RuntimeOptions;
import com.hc.common.exceptions.SmsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@ConditionalOnBean(SmsConfig.class)
public class SmsSender {

    @Autowired
    private SmsConfig smsConfig;

    public void send(String phone, String code) {

        SendSmsRequest request = new SendSmsRequest();
        request.setSignName("团了个团");
        request.setTemplateCode("SMS_475755078");
        request.setPhoneNumbers(phone);

        Map<String, String> map = new HashMap<>();
        map.put("code", code);
        request.setTemplateParam(JSON.toJSONString(map));

        try {
            SendSmsResponse response = send(request);
            log.info("阿里云发送短信 -->> {}", JSON.toJSONString(response));
            SendSmsResponseBody body = response.getBody();
            String responseCode = body.code;
//            if (!"OK".equals(responseCode)) {
//                throw new SmsException();
//            }
        }catch (Exception e) {
            throw new SmsException();
        }

    }

    public SendSmsResponse send(SendSmsRequest request) throws Exception {

        // 配置运行时间选项暂时未进行配置
        RuntimeOptions runtime = new RuntimeOptions();

        //初始化配置信息
        Client client = smsConfig.createClient();
        SendSmsResponse sendSmsResponse;
        try {
            //发送短信
            sendSmsResponse = client.sendSmsWithOptions(request, runtime);
        } catch (Exception e) {
            throw new Exception("调用阿里云发送短信接口失败", e);
        }
        log.info("调用阿里云发送短信接口成功");
        return sendSmsResponse;
    }

}
