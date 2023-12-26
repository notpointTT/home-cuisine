package com.xxx.mng.service;

import com.xxx.mng.entities.SuoPzFundCheckRecordEntity;

import java.util.List;

/**
 * @author a1234
 * @description
 * @create 2023-12-10 20:16
 */
public interface SuoFundService {

    /**
     * 创建某月的资金核对
     * @param month
     */
    void createFundCheck(String month);

    List<SuoPzFundCheckRecordEntity> fundCheckList();

}
