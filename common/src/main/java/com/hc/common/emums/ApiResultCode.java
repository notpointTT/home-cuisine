package com.hc.common.emums;

import com.hc.common.constant.CommonConstant;

public enum ApiResultCode {
    SUCCESS(CommonConstant.YES, ""),
    ERROR(CommonConstant.NO, ""),
    LOGIN_FAIL("E000", "登录失败"),
    TOKEN_EXPIRED("E001", "token已过期"),
    ACCESS_DENIED("E002", "访问被拒绝"),
    SMS_INVALID("E003", "验证码无效"),
    UNSUPPORTED_LOGIN_TYPE("E004", "不支持的登录认证方式"),
    SENTINEL_FLOW_LIMITED("ES001", "流量被限制"),
    SENTINEL_HOT_LIMITED("ES002", "热点流量被限制"),
    SENTINEL_DEGRADE_LIMITED("ES003", "请求被降级限制"),;

    private String code;
    private String msg;

    ApiResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
