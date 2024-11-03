package com.hc.common.dcs.limit;

public abstract class DbConfRateLimiter implements RateLimiter {

    private RateLimitDbConfigurator configurator;
    private String appId;
    private String serverInstance;
    private String urlRegular;

    public DbConfRateLimiter() {
    }

    public DbConfRateLimiter(RateLimitDbConfigurator configurator, String appId, String urlRegular, String serverInstance) {
        this.configurator = configurator;
        this.appId = appId;
        this.urlRegular = urlRegular;
        this.serverInstance = serverInstance;
    }

    int getQps() {
        return configurator.getQps(appId, serverInstance, urlRegular);
    }

    @Override
    public String matchPattern() {
        return urlRegular;
    }

    public RateLimitDbConfigurator getConfigurator() {
        return configurator;
    }

    public void setConfigurator(RateLimitDbConfigurator configurator) {
        this.configurator = configurator;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getUrlRegular() {
        return urlRegular;
    }

    public void setUrlRegular(String urlRegular) {
        this.urlRegular = urlRegular;
    }

    public String getServerInstance() {
        return serverInstance;
    }

    public void setServerInstance(String serverInstance) {
        this.serverInstance = serverInstance;
    }
}
