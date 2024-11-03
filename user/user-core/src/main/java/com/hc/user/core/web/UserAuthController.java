package com.hc.user.core.web;

import com.hc.user.core.model.auth.UsernameLoginModel;
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
@RequestMapping("/user/auth")
@IgnoreAuth
public class UserAuthController {

    @Autowired
    private UserAuthService userAuthService;

    @GetMapping("/sendVerificationCode")
    public ApiResult<?> sendVerificationCode(@RequestParam("phoneNum") String phoneNum) {
        userAuthService.sendVerificationCode(phoneNum);
        return ApiResult.success("验证码发送成功");
    }

    @PostMapping("/registerAndLogin")
    public ApiResult<?> registerAndLogin(@RequestParam("phoneNum") String phoneNum, @RequestParam("verificationCode") String verificationCode) {
        return ApiResult.success(userAuthService.registerAndLogin(phoneNum, verificationCode));
    }

    @PostMapping("/login")
    public ApiResult<?> login(@RequestParam("phoneNum") String phoneNum, @RequestParam("verificationCode") String verificationCode) {
        return ApiResult.success(userAuthService.login(phoneNum, verificationCode));
    }


}