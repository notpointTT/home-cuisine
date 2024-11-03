package com.hc.common.exceptions;

import com.hc.common.constant.CommonConstant;
import com.hc.common.emums.ApiResultCode;

public class UserNotLoginException extends BaseException{

    public UserNotLoginException() {
        super("用户未登录");
        setCode(ApiResultCode.TOKEN_EXPIRED);
    }
}
