package com.hc.user.core.web;

import com.hc.common.annotation.IgnoreAuth;
import com.hc.common.model.ApiResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/info")
//@IgnoreAuth
public class UserInfoController {

    @GetMapping("/base")
    public ApiResult<?> baseInfo() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", "123123");
        map.put("nickname", "张三");
        map.put("avatar", "xxxx");
        map.put("phone", "13012312312");
        map.put("gender", 1);
        map.put("birthday", "1999-09-09");
        return ApiResult.success(map);
    }

}
