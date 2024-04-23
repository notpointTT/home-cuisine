package com.xxx.mng.web;

import com.xxx.common.model.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author a1234
 * @description
 * @create 2024-01-18 15:36
 */
@RestController
@RequestMapping("/flink")
@Slf4j
public class FlinkController {

    @GetMapping("/test")
    public ApiResult<?> test(@RequestParam("v") String v) {
        log.info("flink test v -->> {}", v);
        return ApiResult.success();
    }

}
