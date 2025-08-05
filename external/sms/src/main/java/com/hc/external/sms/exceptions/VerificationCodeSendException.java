package com.hc.external.sms.exceptions;

import com.hc.commons.core.exceptions.BaseException;

public class VerificationCodeSendException extends BaseException {
    public VerificationCodeSendException() {
        super("验证码发送异常，请稍后重试");
    }
}
