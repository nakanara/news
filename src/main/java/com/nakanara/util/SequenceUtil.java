package com.nakanara.util;

import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SequenceUtil {

    private static SequenceUtil sequenceUtil = null;
    private static String currentTime = "";
    private int cnrIndex = 1;

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("N");

    public static SequenceUtil getInstance() {

        if(sequenceUtil == null) {
            return new SequenceUtil();
        }

        return sequenceUtil;
    }

    /**
     * 현재 시간 UID 발급
     * @return
     */
    public String getCurrentTime(){
        LocalDateTime localDate = LocalDateTime.now();

        return localDate.format(dateTimeFormatter);

    }
    public String getVal(){

        boolean isCur = false;
        String curTime = getCurrentTime();

        if(!StringUtils.hasLength(currentTime)) {
            currentTime = curTime;
            cnrIndex = 1;
        } else {

            if(curTime.equals(currentTime)) {
                cnrIndex += 1;
            } else {
                currentTime = curTime;
                cnrIndex = 1;
            }
        }


        return currentTime + cnrIndex;


    }


}
