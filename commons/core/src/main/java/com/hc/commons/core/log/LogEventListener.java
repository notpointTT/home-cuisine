package com.hc.commons.core.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LogEventListener implements ApplicationListener<LogEvent> {

    /**
     * 异步监听日志事件
     * @param event the event to respond to
     */
    @Override
    @Async
    public void onApplicationEvent(LogEvent event) {
        log.info("接收到日志事件 -->> {}", event.getSource());
    }
}
