package com.nakanara.util;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * package : com.nakanara.util
 * class : Time.java
 * date: 2022-05-16 오전 12:54
 * user : jwpark
 * descr : 시간 유틸
 *
 **/


public class TimeUtil {

    final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    public static String getCurDateString(){
        LocalTime localTime = LocalTime.now();

        return localTime.format(DATE_TIME_FORMATTER);
    }

    public static Date getCurDate(){

        return Date.from(LocalDateTime.now()
                .atZone(ZoneId.systemDefault())
                .toInstant());

    }
}
