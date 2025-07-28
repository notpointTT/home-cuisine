package com.hc.common.constant;

/**
 * @author a1234
 * @description
 * @create 2023-12-09 23:04
 */
public final class CommonConstant {

    public static final String YES = "1";
    public static final String NO = "0";

    public static final String TOKEN_HEADER_NAME = "Authority";

    public static final int SMS_CODE_LENGTH = 6;

    public static final class TokenConstant {

        // 全局通用 秘钥
        public static final String SIGN_KEY = "HC_DDD_XXX";
    }

    public static final class LoginType {
        /**
         * 手机短信
         */
        public static final String SMS = "SMS";
        /**
         * 微信
         */
        public static final String WX = "WX";
        /**
         * 常规用户密码
         */
        public static final String USER_PASSWORD = "USER_PASSWORD";
        /**
         * 二维码
         */
        public static final String QR = "QR";
    }

}
