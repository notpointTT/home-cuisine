package com.hc.mng.entities;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author a1234
 * @description
 * @create 2023-12-10 20:57
 */
@TableName("pz投资大学")
public class SuoPzUniversityEntity extends SuoPzFundCheck {

    private String 会员头像;
    private String 买家昵称;
    private String 课程;
    private String 支付金额;
    private String 赠送币金额;
    private String 端;
    private String 订单来源;
    private String 是否全买;
    private String 备注;
    private String 支付渠道;
    private String 推广来源;
    private String 部门类别;
    private String 下单时间;
    private String 过期时间;


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

    public String get课程() {
        return 课程;
    }

    public void set课程(String 课程) {
        this.课程 = 课程;
    }

    public String get支付金额() {
        return 支付金额;
    }

    public void set支付金额(String 支付金额) {
        this.支付金额 = 支付金额;
    }

    public String get赠送币金额() {
        return 赠送币金额;
    }

    public void set赠送币金额(String 赠送币金额) {
        this.赠送币金额 = 赠送币金额;
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

    public String get是否全买() {
        return 是否全买;
    }

    public void set是否全买(String 是否全买) {
        this.是否全买 = 是否全买;
    }

    public String get备注() {
        return 备注;
    }

    public void set备注(String 备注) {
        this.备注 = 备注;
    }

    public String get支付渠道() {
        return 支付渠道;
    }

    public void set支付渠道(String 支付渠道) {
        this.支付渠道 = 支付渠道;
    }

    public String get推广来源() {
        return 推广来源;
    }

    public void set推广来源(String 推广来源) {
        this.推广来源 = 推广来源;
    }

    public String get部门类别() {
        return 部门类别;
    }

    public void set部门类别(String 部门类别) {
        this.部门类别 = 部门类别;
    }

    public String get下单时间() {
        return 下单时间;
    }

    public void set下单时间(String 下单时间) {
        this.下单时间 = 下单时间;
    }

    public String get过期时间() {
        return 过期时间;
    }

    public void set过期时间(String 过期时间) {
        this.过期时间 = 过期时间;
    }
}
