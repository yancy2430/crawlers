package com.upup.crawler.filter;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import com.upup.crawler.bean.LoggerMessage;
import com.upup.crawler.bean.LoggerQueue;

import java.text.DateFormat;
import java.util.Date;
public class LogFilter extends Filter {

    @Override
    public FilterReply decide(Object event) {
        ILoggingEvent event1 = (ILoggingEvent) event;
        LoggerMessage loggerMessage = new LoggerMessage(
                event1.getMessage()
                , DateFormat.getDateTimeInstance().format(new Date(event1.getTimeStamp())),
                event1.getThreadName(),
                event1.getLoggerName(),
                event1.getLevel().levelStr
        );
        LoggerQueue.getInstance().push(loggerMessage);
        return FilterReply.ACCEPT;
    }
}