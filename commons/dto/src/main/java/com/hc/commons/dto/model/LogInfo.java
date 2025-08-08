package com.hc.commons.dto.model;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class LogInfo implements Serializable {

    private Long id;

    private String globalTraceId;
    private String traceId;
    private String logType;

    private String module;
    private String operation;
    private String method;

    private String requestUri;
    private String params;

    private Integer status;
    private String result;
    private String errorMsg;

    private Long executionTime;
    private String operatorId;
    private String operatorName;
    private String clientIp;
    private String userAgent;
    private Date createTime;
}
