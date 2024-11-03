package com.hc.waiter.entities;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author a1234
 * @description
 * @create 2023-12-10 20:30
 */
@TableName("pz打赏")
public class SuoPzRewardEntity extends SuoPzFundCheck{

    private String 头像;
    private String 会员昵称;
    private String 主编;
    private String 栏目;
    private String 文章;
    private String 打赏金额;
    private String 订单号;
    private String 支付渠道;
    private String 端;
    private String 打赏时间;

    public String get头像() {
        return 头像;
    }

    public void set头像(String 头像) {
        this.头像 = 头像;
    }

    public String get会员昵称() {
        return 会员昵称;
    }

    public void set会员昵称(String 会员昵称) {
        this.会员昵称 = 会员昵称;
    }

    public String get主编() {
        return 主编;
    }

    public void set主编(String 主编) {
        this.主编 = 主编;
    }

    public String get栏目() {
        return 栏目;
    }

    public void set栏目(String 栏目) {
        this.栏目 = 栏目;
    }

    public String get文章() {
        return 文章;
    }

    public void set文章(String 文章) {
        this.文章 = 文章;
    }

    public String get打赏金额() {
        return 打赏金额;
    }

    public void set打赏金额(String 打赏金额) {
        this.打赏金额 = 打赏金额;
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

    public String get端() {
        return 端;
    }

    public void set端(String 端) {
        this.端 = 端;
    }

    public String get打赏时间() {
        return 打赏时间;
    }

    public void set打赏时间(String 打赏时间) {
        this.打赏时间 = 打赏时间;
    }
}
