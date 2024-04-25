package com.xxx.user.core.web;

import com.xxx.common.annotation.IgnoreAuth;
import com.xxx.common.model.ApiResult;
import com.xxx.user.core.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author a1234
 * @description
 * @create 2024-04-25 15:06
 */
@RestController
@RequestMapping("/register")
@IgnoreAuth
public class UserRegisterController {

    @Autowired
    private UserRegisterService userRegisterService;

    @GetMapping
    public ApiResult<?> verificationCode(@RequestParam("phoneNum") String phoneNum) {

    }

}
