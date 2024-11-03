package com.hc.common.exceptions;

import com.hc.common.exceptions.BaseException;

public class VerificationCodeSendException extends BaseException {
    public VerificationCodeSendException() {
        super("验证码发送异常，请稍后重试");
    }
}
