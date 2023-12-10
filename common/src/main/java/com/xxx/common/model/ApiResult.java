package com.xxx.common.model;

import com.xxx.common.constant.CommonConstant;

/**
 * @author a1234
 * @description
 * @create 2023-12-09 23:02
 */
public class ApiResult<T> {

    private String code;
    private String msg;
    private T data;

    public static ApiResult<?> success() {
        ApiResult<Object> result = new ApiResult<>();
        result.code = CommonConstant.YES;

        return result;
    }

    public static <T> ApiResult<T> success(T data) {
        ApiResult<T> result = new ApiResult<>();
        result.code = CommonConstant.YES;
        result.data = data;

        return result;
    }

    public static ApiResult<?> error() {
        ApiResult<Object> result = new ApiResult<>();
        result.code = CommonConstant.NO;

        return result;
    }

    public static ApiResult<?> error(String msg) {
        ApiResult<Object> result = new ApiResult<>();
        result.code = CommonConstant.NO;
        result.msg = msg;

        return result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
