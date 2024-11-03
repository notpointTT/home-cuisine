package com.hc.common.dcs.config;

import com.hc.common.dcs.limit.RateLimiter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DcsRequestHandlerInterceptor implements HandlerInterceptor {
    @Value("${spring.application.name}")
    private String appId;
    private String ip;
    @Value("${server.port}")
    private String port;

    private List<RateLimiter> requestRateLimiters = new ArrayList<>();
    private AntPathMatcher urlMatcher = new AntPathMatcher();

    private ReadWriteLock lock = new ReentrantReadWriteLock(true);

    public DcsRequestHandlerInterceptor() {
        try {
            this.ip = InetAddress.getLocalHost().getHostAddress();
        }catch (Exception e) {
            this.ip = "UNKNOW";
        }

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        RateLimiter matchLimiter = null;
        Lock readLock = lock.readLock();
        readLock.lock();
        try {
            for (RateLimiter limiter : requestRateLimiters) {
                boolean match = urlMatcher.match(limiter.matchPattern(), requestURI);
                if (match) {

                }
            }
        }finally {
            readLock.unlock();
        }


        return true;
    }


}
