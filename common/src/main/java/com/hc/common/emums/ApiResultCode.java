package com.hc.common.emums;

import com.hc.common.constant.CommonConstant;

public enum ApiResultCode {
    SUCCESS(CommonConstant.YES, ""),
    ERROR(CommonConstant.NO, ""),
    TOKEN_EXPIRED("001", "token已过期"),
    ACCESS_DENIED("002", "访问被拒绝");

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
