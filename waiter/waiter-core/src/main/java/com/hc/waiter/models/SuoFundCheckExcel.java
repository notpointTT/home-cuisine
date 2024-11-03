package com.hc.waiter.models;

/**
 * @author a1234
 * @description
 * @create 2023-12-27 21:17
 */
public class SuoFundCheckExcel {

    private String 商户号;
    private String 订单号;
    private String 商品名称;
    private String 收款;
    private String 退款;
    private String 到账;
    private String 交易时间;

    private String 业务类型;

    public String get商户号() {
        return 商户号;
    }

    public void set商户号(String 商户号) {
        this.商户号 = 商户号;
    }

    public String get订单号() {
        return 订单号;
    }

    public void set订单号(String 订单号) {
        this.订单号 = 订单号;
    }

    public String get商品名称() {
        return 商品名称;
    }

    public void set商品名称(String 商品名称) {
        this.商品名称 = 商品名称;
    }

    public String get收款() {
        return 收款;
    }

    public void set收款(String 收款) {
        this.收款 = 收款;
    }

    public String get退款() {
        return 退款;
    }

    public void set退款(String 退款) {
        this.退款 = 退款;
    }

    public String get到账() {
        return 到账;
    }

    public void set到账(String 到账) {
        this.到账 = 到账;
    }

    public String get交易时间() {
        return 交易时间;
    }

    public void set交易时间(String 交易时间) {
        this.交易时间 = 交易时间;
    }

    public String get业务类型() {
        return 业务类型;
    }

    public void set业务类型(String 业务类型) {
        this.业务类型 = 业务类型;
    }
}
