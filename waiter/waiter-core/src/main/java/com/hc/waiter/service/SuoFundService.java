package com.hc.waiter.service;

import com.hc.waiter.entities.SuoPzFundCheck;
import com.hc.waiter.entities.SuoPzFundCheckRecordEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
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

    List<? extends SuoPzFundCheck> fundCheckDetailList(String checkId, String type);

    void importFundDetail(MultipartFile file, String checkId, String type);

    void analysisFund(String checkId, MultipartFile file, HttpServletResponse response);

}
