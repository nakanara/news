package com.nakanara;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Slf4j
@Import({WebConfig.class, SecurityConfig.class})
@ComponentScan(value = "com.nakanara.*.*")
public class ApplicationConfig  {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationConfig.class, args);
    }


}
