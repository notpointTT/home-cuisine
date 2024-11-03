package com.hc.waiter.entities;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author a1234
 * @description
 * @create 2023-12-10 20:37
 */
@TableName("pz换课")
public class SuoPzChangeSourceEntity extends SuoPzFundCheck {

    private String 是否加赠;
    private String 工单ID;
    private String 会员ID;
    private String 会员头像;
    private String 昵称;
    private String 被换圈子;
    private String 被换金额;
    private String 被换剩余天数;
    private String 被换剩余金额;
    private String 换至圈子;
    private String 换至圈子天数;
    private String 工单状态;
    private String 备注信息;
    private String 创建时间;

    public String get是否加赠() {
        return 是否加赠;
    }

    public void set是否加赠(String 是否加赠) {
        this.是否加赠 = 是否加赠;
    }

    public String get工单ID() {
        return 工单ID;
    }

    public void set工单ID(String 工单ID) {
        this.工单ID = 工单ID;
    }

    public String get会员ID() {
        return 会员ID;
    }

    public void set会员ID(String 会员ID) {
        this.会员ID = 会员ID;
    }

    public String get会员头像() {
        return 会员头像;
    }

    public void set会员头像(String 会员头像) {
        this.会员头像 = 会员头像;
    }

    public String get昵称() {
        return 昵称;
    }

    public void set昵称(String 昵称) {
        this.昵称 = 昵称;
    }

    public String get被换圈子() {
        return 被换圈子;
    }

    public void set被换圈子(String 被换圈子) {
        this.被换圈子 = 被换圈子;
    }

    public String get被换金额() {
        return 被换金额;
    }

    public void set被换金额(String 被换金额) {
        this.被换金额 = 被换金额;
    }

    public String get被换剩余天数() {
        return 被换剩余天数;
    }

    public void set被换剩余天数(String 被换剩余天数) {
        this.被换剩余天数 = 被换剩余天数;
    }

    public String get被换剩余金额() {
        return 被换剩余金额;
    }

    public void set被换剩余金额(String 被换剩余金额) {
        this.被换剩余金额 = 被换剩余金额;
    }

    public String get换至圈子() {
        return 换至圈子;
    }

    public void set换至圈子(String 换至圈子) {
        this.换至圈子 = 换至圈子;
    }

    public String get换至圈子天数() {
        return 换至圈子天数;
    }

    public void set换至圈子天数(String 换至圈子天数) {
        this.换至圈子天数 = 换至圈子天数;
    }

    public String get工单状态() {
        return 工单状态;
    }

    public void set工单状态(String 工单状态) {
        this.工单状态 = 工单状态;
    }

    public String get备注信息() {
        return 备注信息;
    }

    public void set备注信息(String 备注信息) {
        this.备注信息 = 备注信息;
    }

    public String get创建时间() {
        return 创建时间;
    }

    public void set创建时间(String 创建时间) {
        this.创建时间 = 创建时间;
    }
}
