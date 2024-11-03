package com.hc.common.dcs.limit;

public interface RateLimiter {

    String matchPattern();

    /**
     * 尝试获取流量
     * @return true：获取成功
     */
    boolean tryAcquire();

}
