package com.hc.common.dcs.limit;

import java.util.concurrent.atomic.AtomicInteger;

public class SimpleWindowRateLimiter extends DbConfRateLimiter {


    private static final int WINDOW_TIME = 1000;
    private long startTime = System.currentTimeMillis();
    private AtomicInteger rateCount = new AtomicInteger(0);

    @Override
    public synchronized boolean tryAcquire() {
        int qps = getQps();
        if ((System.currentTimeMillis() - startTime) > WINDOW_TIME) {
            rateCount.set(0);
            startTime = System.currentTimeMillis();
        }
        return rateCount.incrementAndGet() <= qps;
    }
}
