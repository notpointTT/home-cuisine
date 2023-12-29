package com.xxx.mng.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.base.CaseFormat;
import com.xxx.common.exceptions.XxxException;
import com.xxx.common.utils.UUIDUtil;
import com.xxx.mng.entities.*;
import com.xxx.mng.mapper.SuoFundMapper;
import com.xxx.mng.models.SimpleFundInfo;
import com.xxx.mng.models.SuoFundCheckExcel;
import com.xxx.mng.service.SuoFundService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author a1234
 * @description
 * @create 2023-12-10 20:17
 */
@Service
public class SuoFundServiceImpl implements SuoFundService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SuoFundServiceImpl.class);

    public static final Map<String, TypeFund> TYPE_FUND_MAP = new HashMap<>();
    static {
        TYPE_FUND_MAP.put("充值", new TypeFund("pz充值", SuoPzRechargeEntity.class));
        TYPE_FUND_MAP.put("打赏", new TypeFund("pz打赏", SuoPzRewardEntity.class));
        TYPE_FUND_MAP.put("换课", new TypeFund("pz换课", SuoPzChangeSourceEntity.class));
        TYPE_FUND_MAP.put("会员", new TypeFund("pz会员", SuoPzMemberEntity.class));
        TYPE_FUND_MAP.put("视频", new TypeFund("pz视频", SuoPzVideoEntity.class));
        TYPE_FUND_MAP.put("投资大学", new TypeFund("pz投资大学", SuoPzUniversityEntity.class));
        TYPE_FUND_MAP.put("专栏", new TypeFund("pz专栏", SuoPzSpecialEntity.class));
        TYPE_FUND_MAP.put("圈子", new TypeFund("pz圈子", SuoPzGroupEntity.class));
    }

    @Autowired
    private SuoFundMapper fundMapper;

    @Autowired
    private DataSource dataSource;

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

    @Override
    public void importFundDetail(MultipartFile file, String checkId, String type) {
        TypeFund typeFund = TYPE_FUND_MAP.get(type);
        long l = System.currentTimeMillis();
        try {
            fundMapper.deleteFundCheckDetail(typeFund.table, checkId);

            InputStream inputStream = file.getInputStream();
            ImportFundDetailExcelListener excelListener = new ImportFundDetailExcelListener(typeFund, checkId);
            EasyExcel.read(inputStream, typeFund.clazz, excelListener).excelType(ExcelTypeEnum.CSV).doReadAll();
            inputStream.close();

        }catch (Exception e) {
            throw new XxxException(e);
        }finally {
            LOGGER.info("----- {}", System.currentTimeMillis() - l);
        }
    }

    @Override
    public void analysisFund(String checkId, MultipartFile file, HttpServletResponse response) {
        try {
            List<SimpleFundInfo> allSimpleFundList = fundMapper.findAllSimpleFundList(checkId);
            InputStream inputStream = file.getInputStream();
            FundCheckExcelListener readListener = new FundCheckExcelListener(allSimpleFundList);
            EasyExcel.read(inputStream, SuoFundCheckExcel.class, readListener).doReadAll();
            inputStream.close();
            ServletOutputStream outputStream = response.getOutputStream();
            EasyExcel.write(outputStream, SuoFundCheckExcel.class).sheet().doWrite(readListener.dataList);

            response.flushBuffer();
        }catch (Exception e) {
            throw new XxxException(e);
        }

    }

    class ImportFundDetailExcelListener<T extends SuoPzFundCheck> extends AnalysisEventListener<T> {

        private static final int SAVE_SIZE = 100;
        private TypeFund typeFund;
        private String checkId;
        private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        private List<ColumnMethodInfo> columnMethodInfos = new ArrayList<>();

        private List<T> dataList = new ArrayList<>(SAVE_SIZE);

        public ImportFundDetailExcelListener(TypeFund typeFund, String checkId) {
            this.typeFund = typeFund;
            this.checkId = checkId;
            initSqlColumns();
        }

        @Override
        public void invoke(T data, AnalysisContext context) {
            data.setCheckId(checkId);
            dataList.add(data);
            if (dataList.size() >= SAVE_SIZE) {
                executeSql(dataList);
                dataList.clear();
            }

        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            if (!dataList.isEmpty()) {
                fundMapper.executeSql(generateSql(dataList));
                dataList.clear();
            }
        }

        void initSqlColumns() {
            Class<? extends SuoPzFundCheck> clazz = typeFund.clazz;
            Method[] methods = clazz.getMethods();
            for (Method method: methods) {
                method.setAccessible(true);

                String name = method.getName();
                if (!name.startsWith("get") || name.equals("getClass")) {
                    continue;
                }
                ColumnMethodInfo columnMethodInfo = new ColumnMethodInfo();
                String s = name.replace("get", "");

                columnMethodInfo.columnName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, s);
                columnMethodInfo.method = method;
                columnMethodInfos.add(columnMethodInfo);
            }
        }

        void executeSql(List<T> dataList) {
            Connection connection = null;
            try {
                connection = dataSource.getConnection();
                PrepareSqlStringParams prepareSqlStringParams = generatePrepareSql(dataList);
                PreparedStatement preparedStatement = connection.prepareStatement(prepareSqlStringParams.sql);
                for (int i = 0; i < prepareSqlStringParams.params.size(); i++) {
                    preparedStatement.setString(i+1, prepareSqlStringParams.params.get(i));
                }

            }catch (Exception e) {
                throw new XxxException(e);
            }finally {
                if (connection != null) {
                    try {
                        connection.close();
                    }catch (Exception e) {
                        LOGGER.error("", e);
                    }

                }
            }

        }

        PrepareSqlStringParams generatePrepareSql(List<T> dataList) {
            List<String> params = new ArrayList<>();
            StringBuilder sb = new StringBuilder("insert into ");
            sb.append(typeFund.table).append(" ");
            StringBuilder sqlColumns = new StringBuilder(" ( ");
//            StringBuilder sqlValues = new StringBuilder(" ");
            sqlColumns.append(
                    columnMethodInfos.stream()
                            .map(ColumnMethodInfo::getColumnName)
                            .collect(Collectors.joining(" , "))
            ).append(" ) ");
            String sqlValues = dataList.stream().map(data -> {
                StringBuilder oneSqlValues = new StringBuilder();
                oneSqlValues.append(" ( ").append(
                        columnMethodInfos.stream()
                                .map(info -> {
                                    try {
                                        String value = getObjValue(data, info.method);
                                        params.add(value);
                                    }catch (Exception e){
                                        params.add("");
                                    }
                                    return "?";
                                })
                                .collect(Collectors.joining(" , "))
                ).append(" ) ");
                return oneSqlValues.toString();
            }).collect(Collectors.joining(","));

            sb.append(sqlColumns).append(" values ").append(sqlValues);

            PrepareSqlStringParams prepareSqlStringParams = new PrepareSqlStringParams();
            prepareSqlStringParams.sql = sb.toString();;
            prepareSqlStringParams.params = params;
            return prepareSqlStringParams;
        }

        String generateSql(List<T> dataList) {
            StringBuilder sb = new StringBuilder("insert into ");
            sb.append(typeFund.table).append(" ");
            StringBuilder sqlColumns = new StringBuilder(" ( ");
//            StringBuilder sqlValues = new StringBuilder(" ");
            sqlColumns.append(
                    columnMethodInfos.stream()
                            .map(ColumnMethodInfo::getColumnName)
                            .collect(Collectors.joining(" , "))
            ).append(" ) ");
            String sqlValues = dataList.stream().map(data -> {
                StringBuilder oneSqlValues = new StringBuilder();
                oneSqlValues.append(" ( ").append(
                        columnMethodInfos.stream()
                        .map(info -> {
                            try {
                                Object obj = info.method.invoke(data);
                                if (!(obj instanceof Date)) {
                                    return obj == null ? "''" : ("'" + obj.toString() + "'");
                                }
                                return simpleDateFormat.format(obj);
                            }catch (Exception e){
                                return "''";
                            }
                        })
                        .collect(Collectors.joining(" , "))
                ).append(" ) ");
                return oneSqlValues.toString();
            }).collect(Collectors.joining(","));

            sb.append(sqlColumns).append(" values ").append(sqlValues);

            return sb.toString();
        }

        String getObjValue(T data, Method method) throws Exception {
            Object obj = method.invoke(data);
            if (!(obj instanceof Date)) {
                return obj == null ? "''" : ("'" + obj.toString() + "'");
            }
            return simpleDateFormat.format(obj);
        }

    }

    class PrepareSqlStringParams {
        private String sql;
        private List<String> params;


    }

    class ColumnMethodInfo {
        private String columnName;
        private Method method;

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        public Method getMethod() {
            return method;
        }

        public void setMethod(Method method) {
            this.method = method;
        }
    }

    class FundCheckExcelListener extends AnalysisEventListener<SuoFundCheckExcel> {
        private List<SuoFundCheckExcel> dataList = new ArrayList<>();

        private List<SimpleFundInfo> simpleFundInfoList;

        public FundCheckExcelListener(List<SimpleFundInfo> simpleFundInfoList) {
            this.simpleFundInfoList = simpleFundInfoList;
        }

        @Override
        public void invoke(SuoFundCheckExcel data, AnalysisContext context) {
            for (SimpleFundInfo f: simpleFundInfoList) {
                String 订单号 = f.get订单号();
                String 业务类型 = f.get业务类型();
                String bt = data.get业务类型();
                List<String> l = new ArrayList<>(2);
                if (!StringUtils.isEmpty(bt)) {
                    l.add(bt);
                }
                if (订单号.contains(data.get订单号())) {
                    l.add(业务类型);

                } else if (业务类型.equals("会员") && 订单号.equals(data.get交易时间() + ">>>" + data.get收款())) {
                    l.add(业务类型);
                }

                data.set业务类型(String.join(",", l));

            }
            dataList.add(data);
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {

        }
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
