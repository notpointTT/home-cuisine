package com.hc.waiter.mapper;

import com.hc.waiter.models.SimpleFundInfo;
import com.hc.waiter.entities.SuoPzFundCheckRecordEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author a1234
 * @description
 * @create 2023-12-10 17:54
 */
@Repository
public interface SuoFundMapper {

    /**
     * 新增账单核对
     * @param fundCheckRecordEntity
     */
    void createFundCheck(SuoPzFundCheckRecordEntity fundCheckRecordEntity);

    List<SuoPzFundCheckRecordEntity> selectFundCheckList();

    List<Map<String, Object>> fundCheckDetailList(@Param("table") String table, @Param("checkId") String checkId);

    void deleteFundCheckDetail(@Param("table") String table, @Param("checkId") String checkId);

    List<SimpleFundInfo> findAllSimpleFundList(@Param("checkId") String checkId);

    void executeSql(String sql);

}
