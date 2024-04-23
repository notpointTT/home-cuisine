package com.xxx.mng.entities;

import java.util.Date;

/**
 * @author a1234
 * @description
 * @create 2023-12-14 16:19
 */
public class SuoPzFundCheckRecordEntity {

    private String id;
    private String month;
    private Date startTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}
