package com.nakanara;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class Test {

    @org.junit.jupiter.api.Test
    public void test1(){

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd.HH.mm.ss.N");
        LocalDateTime localDate = LocalDateTime.now();

        System.out.println(localDate.format(dateTimeFormatter));
        System.out.println(">>" + localDate.getNano());

    }
}
