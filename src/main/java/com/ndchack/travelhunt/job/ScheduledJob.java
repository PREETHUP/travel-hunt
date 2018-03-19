package com.ndchack.travelhunt.job;

import com.ndchack.travelhunt.Util.Configuration;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.Seconds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class ScheduledJob {

    @Autowired
    private ScheduledAnnotationBeanPostProcessor postProcessor;

    @Autowired
    private Configuration configuration;

    private static final Logger log = LoggerFactory.getLogger(ScheduledJob.class);
    private boolean stop = false;

    @Scheduled(fixedRate = 10000, initialDelay = 50000)
    public void checkTime() {
        if (configuration.returnDepartureTime != null) {
            DateTime returnDepartureTime = configuration.returnDepartureTime
                    .withSecondOfMinute(0)
                    .withMinuteOfHour(0)
                    .withMillisOfSecond(0);
            log.info(String.valueOf(returnDepartureTime));
            DateTime currentTime = new DateTime()
                    .withSecondOfMinute(0)
                    .withMinuteOfHour(0)
                    .withMillisOfSecond(0)
                    .minusHours(6);
            log.info("Checking if we should trigger the notification.");
            if (currentTime.isAfter(returnDepartureTime.minusHours(6)) && stop != true) {
                stop = true;
                Configuration.gameStage = "3";
                log.info("Completed Cron Job !!");
                postProcessor.destroy();
            }
        }
    }
}
