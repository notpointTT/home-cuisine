package com.hc.mng.entities;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author a1234
 * @description
 * @create 2023-12-10 20:24
 */
@TableName("pz充值")
public class SuoPzRechargeEntity extends SuoPzFundCheck {

    private String 头像;
    private String 昵称;
    private String 充值金额;
    private String 手续费;
    private String 毛收入;
    private String 支付状态;
    private String 端;
    private String 订单号;
    private String 支付渠道;
    private String 交易单号;
    private String 充值时间;

    public String get头像() {
        return 头像;
    }

    public void set头像(String 头像) {
        this.头像 = 头像;
    }

    public String get昵称() {
        return 昵称;
    }

    public void set昵称(String 昵称) {
        this.昵称 = 昵称;
    }

    public String get充值金额() {
        return 充值金额;
    }

    public void set充值金额(String 充值金额) {
        this.充值金额 = 充值金额;
    }

    public String get手续费() {
        return 手续费;
    }

    public void set手续费(String 手续费) {
        this.手续费 = 手续费;
    }

    public String get毛收入() {
        return 毛收入;
    }

    public void set毛收入(String 毛收入) {
        this.毛收入 = 毛收入;
    }

    public String get支付状态() {
        return 支付状态;
    }

    public void set支付状态(String 支付状态) {
        this.支付状态 = 支付状态;
    }

    public String get端() {
        return 端;
    }

    public void set端(String 端) {
        this.端 = 端;
    }

    public String get订单号() {
        return 订单号;
    }

    public void set订单号(String 订单号) {
        this.订单号 = 订单号;
    }

    public String get支付渠道() {
        return 支付渠道;
    }

    public void set支付渠道(String 支付渠道) {
        this.支付渠道 = 支付渠道;
    }

    public String get交易单号() {
        return 交易单号;
    }

    public void set交易单号(String 交易单号) {
        this.交易单号 = 交易单号;
    }

    public String get充值时间() {
        return 充值时间;
    }

    public void set充值时间(String 充值时间) {
        this.充值时间 = 充值时间;
    }
}
