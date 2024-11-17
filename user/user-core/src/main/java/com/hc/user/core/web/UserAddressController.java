package com.hc.user.core.web;

import com.hc.common.annotation.IgnoreAuth;
import com.hc.common.model.ApiResult;
import com.hc.user.core.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
@IgnoreAuth
public class UserAddressController {

    @Autowired
    private UserAddressService service;

    @GetMapping("/list")
    public ApiResult<?> list() {
        return ApiResult.success(service.list());
    }

    @PostMapping("/set-default/{id}")
    public ApiResult<?> setDefault(@PathVariable("id") String id) {
        service.setDefault(id);
        return ApiResult.success();
    }

}
