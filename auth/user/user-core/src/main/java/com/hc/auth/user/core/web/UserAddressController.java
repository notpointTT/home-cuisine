package com.hc.auth.user.core.web;

import com.hc.auth.user.core.entities.UserAddressEntity;
import com.hc.auth.user.core.service.UserAddressService;
import com.hc.commons.dto.response.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class UserAddressController {

    @Autowired
    private UserAddressService service;

    @GetMapping("/list")
    public ApiResult<?> list() {
        return ApiResult.success(service.list());
    }

    @GetMapping("/detail/{id}")
    public ApiResult<?> detail(@PathVariable("id") String id) {
        return ApiResult.success(service.detail(id));
    }

    @PostMapping("/update/{id}")
    public ApiResult<?> update(@PathVariable("id") String id, @RequestBody UserAddressEntity address) {
        service.update(id, address);
        return ApiResult.success();
    }

    @PostMapping("/add")
    public ApiResult<?> add(@RequestBody UserAddressEntity address) {
        service.add(address);
        return ApiResult.success();
    }

    @PostMapping("/set-default/{id}")
    public ApiResult<?> setDefault(@PathVariable("id") String id) {
        service.setDefault(id);
        return ApiResult.success();
    }

}
