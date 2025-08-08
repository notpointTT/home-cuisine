package com.hc.commons.dto.constant;

/**
 * @author a1234
 * @description
 * @create 2023-12-09 23:04
 */
public final class CommonConstant {

    public static final String YES = "1";
    public static final String NO = "0";



    public static final int SMS_CODE_LENGTH = 6;

    public static final class Headers {
        public static final String TOKEN_HEADER = "Authorization";
        public static final String X_USER_ID_HEADER = "X-User-Id";
        public static final String X_USER_ROLES_HEADER = "X-User-Roles";
        public static final String X_REQUEST_FROM_HEADER = "X-Request-From";

        /**
         * 全局链路ID
         */
        public static final String X_G_TRACE_ID_HEADER = "X-G-Trace-ID";
        /**
         * 当前链路ID
         */
        public static final String X_TRACE_ID_HEADER = "X-Trace-ID";

        public static final String X_REQUEST_FROM_GATEWAY = "GATEWAY";
        public static final String X_REQUEST_FROM_FEIGN = "FEIGN";
    }

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
