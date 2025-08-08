package com.hc.commons.dto.emums;


import com.hc.commons.dto.constant.CommonConstant;

/**
 * @author a1234
 */

public enum ApiResultCode {
    /**
     * 通用成功
     */
    SUCCESS(CommonConstant.YES, "", 200),
    /**
     * 通用失败
     */
    ERROR(CommonConstant.NO, "", 500),
    /**
     * 登录失败
     */
    LOGIN_FAIL("E000", "登录失败"),
    /**
     * token 过期/无效
     */
    TOKEN_EXPIRED("E001", "token已过期", 401),
    /**
     * 访问被拒绝
     */
    ACCESS_DENIED("E002", "访问被拒绝", 403),
    /**
     * 验证码无效
     */
    SMS_INVALID("E003", "验证码无效"),
    /**
     * 不支持的登录认证方式
     */
    UNSUPPORTED_LOGIN_TYPE("E004", "不支持的登录认证方式"),
    /**
     * 流量被限制/QPS/TPS过高
     * 对应 TOO_MANY_REQUEST
     */
    SENTINEL_FLOW_LIMITED("ES001", "流量被限制", 429),
    /**
     * 热点数据被限制
     */
    SENTINEL_HOT_LIMITED("ES002", "热点数据被限制", 429),
    /**
     * 请求被降级限制
     * 服务不可用
     */
    SENTINEL_DEGRADE_LIMITED("ES003", "请求被降级限制", 503),

    SERVICE_UNAVAILABLE("ES003", "服务不可用", 503),
    ;

    /**
     * web 状态码
     * 默认是 500
     * 一般成功的响应只有 SUCCESS 200，会手动指定
     * 其余 CODE 默认为失败响应 为 500
     */
    private int responseStatus = 500;
    /**
     * 响应码
     */
    private String code;
    private String msg;

    ApiResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    ApiResultCode(String code, String msg, int responseStatus) {
        this.code = code;
        this.msg = msg;
        this.responseStatus = responseStatus;
    }


    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public int getResponseStatus() {
        return responseStatus;
    }
}
