package com.hc.auth.user.core.web;

import com.hc.auth.user.core.service.UserAuthService;
import com.hc.commons.dto.response.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author a1234
 * @description
 * @create 2024-04-25 16:51
 */
@RestController
@RequestMapping("/auth")
public class UserAuthController {

    @Autowired
    private UserAuthService userAuthService;

    @GetMapping("/sms/send")
    public ApiResult<?> sendSms(@RequestParam("phone") String phoneNum) {
        userAuthService.sendSms(phoneNum);
        return ApiResult.success("验证码发送成功");
    }

    @PostMapping("/register")
    public ApiResult<?> register(@RequestParam("phone") String phoneNum, @RequestParam("smsCode") String smsCode) {
        return ApiResult.success(userAuthService.register(phoneNum, smsCode));
    }


}
