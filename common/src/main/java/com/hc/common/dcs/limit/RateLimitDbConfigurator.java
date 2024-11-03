package com.hc.common.dcs.limit;

public interface RateLimitDbConfigurator {

    /**
     * 获取配置 qps
     * @param appId
     * @param serverInstance
     * @param url
     * @return
     */
    int getQps(String appId, String serverInstance, String url);

}
