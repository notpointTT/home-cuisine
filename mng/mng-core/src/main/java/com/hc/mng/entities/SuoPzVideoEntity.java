package com.hc.mng.entities;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author a1234
 * @description
 * @create 2023-12-10 20:54
 */
@TableName("pz视频")
public class SuoPzVideoEntity extends SuoPzFundCheck {

    private String 头像;
    private String 买家昵称;
    private String 视频名称;
    private String 单节名称;
    private String 实付金额;
    private String 赠送币金额;
    private String 平账金额;
    private String 状态;
    private String 推广来源;
    private String 订单平台;
    private String 订单渠道;
    private String 采集信息;
    private String 订单号;
    private String 支付渠道;
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

    public String get视频名称() {
        return 视频名称;
    }

    public void set视频名称(String 视频名称) {
        this.视频名称 = 视频名称;
    }

    public String get单节名称() {
        return 单节名称;
    }

    public void set单节名称(String 单节名称) {
        this.单节名称 = 单节名称;
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

    public String get平账金额() {
        return 平账金额;
    }

    public void set平账金额(String 平账金额) {
        this.平账金额 = 平账金额;
    }

    public String get状态() {
        return 状态;
    }

    public void set状态(String 状态) {
        this.状态 = 状态;
    }

    public String get推广来源() {
        return 推广来源;
    }

    public void set推广来源(String 推广来源) {
        this.推广来源 = 推广来源;
    }

    public String get订单平台() {
        return 订单平台;
    }

    public void set订单平台(String 订单平台) {
        this.订单平台 = 订单平台;
    }

    public String get订单渠道() {
        return 订单渠道;
    }

    public void set订单渠道(String 订单渠道) {
        this.订单渠道 = 订单渠道;
    }

    public String get采集信息() {
        return 采集信息;
    }

    public void set采集信息(String 采集信息) {
        this.采集信息 = 采集信息;
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

    public String get下单时间() {
        return 下单时间;
    }

    public void set下单时间(String 下单时间) {
        this.下单时间 = 下单时间;
    }
}
