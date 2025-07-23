package com.hc.user.core.web;

import com.hc.common.annotation.IgnoreAuth;
import com.hc.common.model.ApiResult;
import com.hc.user.core.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author a1234
 * @description
 * @create 2024-04-25 16:51
 */
@RestController
@RequestMapping("/auth")
@IgnoreAuth
public class UserAuthController {

    @Autowired
    private UserAuthService userAuthService;

    @GetMapping("/sms/send")
    public ApiResult<?> sendSms(@RequestParam("phone") String phoneNum) {
        userAuthService.sendSms(phoneNum);
        return ApiResult.success("验证码发送成功");
    }

    @PostMapping("/registerAndLogin")
    public ApiResult<?> registerAndLogin(@RequestParam("phone") String phoneNum, @RequestParam("smsCode") String smsCode) {
        return ApiResult.success(userAuthService.registerAndLogin(phoneNum, smsCode));
    }

    @PostMapping("/login")
    public ApiResult<?> login(@RequestParam("phone") String phoneNum, @RequestParam("smsCode") String smsCode) {
        return ApiResult.success(userAuthService.login(phoneNum, smsCode));
    }


}
