package com.hc.user.core.web;

import com.hc.common.annotation.IgnoreAuth;
import com.hc.common.model.ApiResult;
import com.hc.user.core.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;

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
    public ApiResult<?> registerAndLogin(@RequestParam("phone") String phoneNum, @RequestParam("smsCode") String smsCode) {
        return ApiResult.success(userAuthService.registerAndLogin(phoneNum, smsCode));
    }


}
