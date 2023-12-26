package com.xxx.mng.entities;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author a1234
 * @description
 * @create 2023-12-10 20:48
 */
@TableName("pz会员")
public class SuoPzMemberEntity extends SuoPzFundCheck {

    private String 头像;
    private String 买家昵称;
    private String 会员电话;
    private String 会员种类;
    private String 会员类型;
    private String 实付金额;
    private String 赠送币金额;
    private String 退款金额;
    private String 会员有效期;
    private String 订单信息;
    private String 订单号;
    private String 支付渠道;
    private String 订单渠道;
    private String 端;
    private String 下单时间;

    public String get头像() {
        return 头像;
    }

    public void set头像(String 头像) {
        this.头像 = 头像;
    }

    public String get买家昵称() {
        return 买家昵称;
    }

    public void set买家昵称(String 买家昵称) {
        this.买家昵称 = 买家昵称;
    }

    public String get会员电话() {
        return 会员电话;
    }

    public void set会员电话(String 会员电话) {
        this.会员电话 = 会员电话;
    }

    public String get会员种类() {
        return 会员种类;
    }

    public void set会员种类(String 会员种类) {
        this.会员种类 = 会员种类;
    }

    public String get会员类型() {
        return 会员类型;
    }

    public void set会员类型(String 会员类型) {
        this.会员类型 = 会员类型;
    }

    public String get实付金额() {
        return 实付金额;
    }

    public void set实付金额(String 实付金额) {
        this.实付金额 = 实付金额;
    }

    public String get赠送币金额() {
        return 赠送币金额;
    }

    public void set赠送币金额(String 赠送币金额) {
        this.赠送币金额 = 赠送币金额;
    }

    public String get退款金额() {
        return 退款金额;
    }

    public void set退款金额(String 退款金额) {
        this.退款金额 = 退款金额;
    }

    public String get会员有效期() {
        return 会员有效期;
    }

    public void set会员有效期(String 会员有效期) {
        this.会员有效期 = 会员有效期;
    }

    public String get订单信息() {
        return 订单信息;
    }

    public void set订单信息(String 订单信息) {
        this.订单信息 = 订单信息;
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

    public String get订单渠道() {
        return 订单渠道;
    }

    public void set订单渠道(String 订单渠道) {
        this.订单渠道 = 订单渠道;
    }

    public String get端() {
        return 端;
    }

    public void set端(String 端) {
        this.端 = 端;
    }

    public String get下单时间() {
        return 下单时间;
    }

    public void set下单时间(String 下单时间) {
        this.下单时间 = 下单时间;
    }
}
