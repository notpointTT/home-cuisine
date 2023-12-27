package com.xxx.mng.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.xxx.common.utils.UUIDUtil;
import com.xxx.mng.entities.*;
import com.xxx.mng.mapper.SuoFundMapper;
import com.xxx.mng.service.SuoFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author a1234
 * @description
 * @create 2023-12-10 20:17
 */
@Service
public class SuoFundServiceImpl implements SuoFundService {

    public static final Map<String, TypeFund> TYPE_FUND_MAP = new HashMap<>();
    static {
        TYPE_FUND_MAP.put("充值", new TypeFund("pz充值", SuoPzRechargeEntity.class));
        TYPE_FUND_MAP.put("打赏", new TypeFund("pz打赏", SuoPzRewardEntity.class));
        TYPE_FUND_MAP.put("换课", new TypeFund("pz换课", SuoPzChangeSourceEntity.class));
        TYPE_FUND_MAP.put("会员", new TypeFund("pz会员", SuoPzMemberEntity.class));
        TYPE_FUND_MAP.put("视频", new TypeFund("pz视频", SuoPzVideoEntity.class));
        TYPE_FUND_MAP.put("投资大学", new TypeFund("pz投资大学", SuoPzUniversityEntity.class));
        TYPE_FUND_MAP.put("专栏", new TypeFund("pz专栏", SuoPzSpecialEntity.class));
    }

    @Autowired
    private SuoFundMapper fundMapper;

    @Override
    public void createFundCheck(String month) {

        SuoPzFundCheckRecordEntity entity = new SuoPzFundCheckRecordEntity();
        entity.setId(UUIDUtil.uuid());
        entity.setMonth(month);
        entity.setStartTime(new Date());
        fundMapper.createFundCheck(entity);
    }

    @Override
    public List<SuoPzFundCheckRecordEntity> fundCheckList() {
        return fundMapper.selectFundCheckList();
    }

    @Override
    public List<? extends SuoPzFundCheck> fundCheckDetailList(String checkId, String type) {
        TypeFund typeFund = TYPE_FUND_MAP.get(type);

        List<Map<String, Object>> maps = fundMapper.fundCheckDetailList(typeFund.table, checkId);
        JSONArray jsonArray = new JSONArray((List) maps);


        return jsonArray.toJavaList(typeFund.clazz);
    }

    static class TypeFund {
        String table;
        Class<? extends SuoPzFundCheck> clazz;

        public TypeFund(String table, Class<? extends SuoPzFundCheck> clazz) {
            this.table = table;
            this.clazz = clazz;
        }
    }
}
