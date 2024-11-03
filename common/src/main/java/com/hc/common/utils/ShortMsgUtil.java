package com.hc.common.utils;

/**
 * 短信工具类
 */
public final class ShortMsgUtil {

    /**
     * 发送短信验证码
     * @param template
     * @param phoneNum
     * @param code
     * @return 发送成功，返回 code
     */
    public static String sendVerificationCode(String template, String phoneNum, String code) {
        // todo 调用阿里云短信服务接口进行发送短信验证码
        return code;
    }

}
