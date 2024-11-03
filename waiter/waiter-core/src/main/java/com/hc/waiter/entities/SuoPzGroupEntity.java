package com.hc.waiter.entities;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author a1234
 * @description
 * @create 2023-12-28 10:44
 */
@TableName("pz圈子")
public class SuoPzGroupEntity extends SuoPzFundCheck {

    private String 会员头像 ;
    /**  */
    private String 买家昵称 ;
    /**  */
    private String 会员电话 ;
    /**  */
    private String 圈子 ;
    /**  */
    private String 购买份数 ;
    /**  */
    private String 实付金额 ;
    /**  */
    private String 赠送币金额 ;
    /**  */
    private String 退款金额 ;
    /**  */
    private String 平账金额 ;
    /**  */
    private String 有效期 ;
    /**  */
    private String 订单号 ;
    /**  */
    private String 支付渠道 ;
    /**  */
    private String 支付时间 ;
    /**  */
    private String 推广来源 ;
    /**  */
    private String 端 ;
    /**  */
    private String 订单来源 ;
    /**  */
    private String 社群推广 ;
    /**  */
    private String 部门类别 ;
    /**  */
    public String get会员头像() {
        return 会员头像;
    }

    public void set会员头像(String 会员头像) {
        this.会员头像 = 会员头像;
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

    public String get圈子() {
        return 圈子;
    }

    public void set圈子(String 圈子) {
        this.圈子 = 圈子;
    }

    public String get购买份数() {
        return 购买份数;
    }

    public void set购买份数(String 购买份数) {
        this.购买份数 = 购买份数;
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

    public String get有效期() {
        return 有效期;
    }

    public void set有效期(String 有效期) {
        this.有效期 = 有效期;
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

    public String get支付时间() {
        return 支付时间;
    }

    public void set支付时间(String 支付时间) {
        this.支付时间 = 支付时间;
    }

    public String get推广来源() {
        return 推广来源;
    }

    public void set推广来源(String 推广来源) {
        this.推广来源 = 推广来源;
    }

    public String get端() {
        return 端;
    }

    public void set端(String 端) {
        this.端 = 端;
    }

    public String get订单来源() {
        return 订单来源;
    }

    public void set订单来源(String 订单来源) {
        this.订单来源 = 订单来源;
    }

    public String get社群推广() {
        return 社群推广;
    }

    public void set社群推广(String 社群推广) {
        this.社群推广 = 社群推广;
    }

    public String get部门类别() {
        return 部门类别;
    }

    public void set部门类别(String 部门类别) {
        this.部门类别 = 部门类别;
    }

}
