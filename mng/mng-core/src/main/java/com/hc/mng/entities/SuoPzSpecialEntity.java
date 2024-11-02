package com.hc.mng.entities;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author a1234
 * @description
 * @create 2023-12-10 21:03
 */
@TableName("pz专栏")
public class SuoPzSpecialEntity extends SuoPzFundCheck {

    private String 会员头像;
    private String 会员昵称;
    private String 会员电话;
    private String 主编;
    private String 专栏;
    private String 实付金额;
    private String 赠送币金额;
    private String 退款金额;
    private String 平账金额;
    private String 专栏有效期;
    private String 订单号;
    private String 支付渠道;
    private String 优惠信息;
    private String 类型;
    private String 订单渠道;
    private String 下单时间;

    public String get会员头像() {
        return 会员头像;
    }

    public void set会员头像(String 会员头像) {
        this.会员头像 = 会员头像;
    }

    public String get会员昵称() {
        return 会员昵称;
    }

    public void set会员昵称(String 会员昵称) {
        this.会员昵称 = 会员昵称;
    }

    public String get会员电话() {
        return 会员电话;
    }

    public void set会员电话(String 会员电话) {
        this.会员电话 = 会员电话;
    }

    public String get主编() {
        return 主编;
    }

    public void set主编(String 主编) {
        this.主编 = 主编;
    }

    public String get专栏() {
        return 专栏;
    }

    public void set专栏(String 专栏) {
        this.专栏 = 专栏;
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

    public String get平账金额() {
        return 平账金额;
    }

    public void set平账金额(String 平账金额) {
        this.平账金额 = 平账金额;
    }

    public String get专栏有效期() {
        return 专栏有效期;
    }

    public void set专栏有效期(String 专栏有效期) {
        this.专栏有效期 = 专栏有效期;
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

    public String get优惠信息() {
        return 优惠信息;
    }

    public void set优惠信息(String 优惠信息) {
        this.优惠信息 = 优惠信息;
    }

    public String get类型() {
        return 类型;
    }

    public void set类型(String 类型) {
        this.类型 = 类型;
    }

    public String get订单渠道() {
        return 订单渠道;
    }

    public void set订单渠道(String 订单渠道) {
        this.订单渠道 = 订单渠道;
    }

    public String get下单时间() {
        return 下单时间;
    }

    public void set下单时间(String 下单时间) {
        this.下单时间 = 下单时间;
    }
}
