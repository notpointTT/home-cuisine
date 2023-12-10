package com.xxx.mng.web;

import com.xxx.common.model.ApiResult;
import com.xxx.mng.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author a1234
 * @description
 * @create 2023-12-09 22:54
 */
@RestController
@RequestMapping("/suo")
public class SuoController {

    @Autowired
    TestMapper testMapper;

    @GetMapping("/demo")
    public ApiResult<?> demo() {
        return ApiResult.success(testMapper.selectAll());
    }

}
