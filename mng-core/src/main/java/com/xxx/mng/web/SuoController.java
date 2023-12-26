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

    @PostMapping("/fundCheck/create")
    public ApiResult<?> createFundCheck(@RequestParam("month") String month) {
        fundService.createFundCheck(month);
        return ApiResult.success(month);
    }

    @GetMapping("/fundCheck/list")
    public ApiResult<?> fundCheckList() {
        return ApiResult.success(fundService.fundCheckList());
    }

    @GetMapping("/fundCheck/detail/list")
    public ApiResult<?> fundCheckDetailList(@RequestParam("checkId") String checkId,
                                            @RequestParam("type") String type) {
        return ApiResult.success(fundService.fundCheckDetailList(checkId, type));
    }

}
