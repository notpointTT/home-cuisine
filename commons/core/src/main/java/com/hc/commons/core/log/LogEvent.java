package com.hc.commons.core.log;

import com.hc.commons.dto.model.LogInfo;
import org.springframework.context.ApplicationEvent;

public class LogEvent extends ApplicationEvent {

    public LogEvent(LogInfo source) {
        super(source);
    }

    @Override
    public LogInfo getSource() {
        return (LogInfo) super.getSource();
    }
}
