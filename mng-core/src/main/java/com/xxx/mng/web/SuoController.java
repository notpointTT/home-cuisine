package com.xxx.mng.web;

import com.xxx.common.model.ApiResult;
import com.xxx.mng.service.SuoFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author a1234
 * @description
 * @create 2023-12-09 22:54
 */
@RestController
@RequestMapping("/suo")
public class SuoController {

    @Autowired
    SuoFundService fundService;

    @PostMapping("/create/fundCheck")
    public ApiResult<?> createFundCheck(@RequestParam("month") String month) {
        fundService.createFundCheck(month);
        return ApiResult.success(month);
    }

    @GetMapping("/list")
    public ApiResult<?> fundCheckList() {
        return ApiResult.success();
    }

}
