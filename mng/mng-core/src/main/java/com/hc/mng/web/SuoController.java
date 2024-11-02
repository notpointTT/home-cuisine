package com.hc.mng.web;

import com.xxx.common.model.ApiResult;
import com.hc.mng.service.SuoFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

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

    @PostMapping("/fundCheck/analysis")
    public void analysisFund(@RequestParam("checkId") String checkId,
                             @RequestParam("file") MultipartFile file,
                             HttpServletResponse response) {
        fundService.analysisFund(checkId, file, response);
    }

    @PostMapping("/fundCheck/importDetail")
    public ApiResult<?> importFundDetail(@RequestParam("checkId") String checkId,
                             @RequestParam("file") MultipartFile file,
                             @RequestParam("type") String type) {
        fundService.importFundDetail(file, checkId, type);
        return ApiResult.success();
    }

}
