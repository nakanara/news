package com.nakanara.news.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@Slf4j
@RestController
@Profile("!real")
public class SystemController {

    @Value("${app.testmode}")
    private String testmode;

    @Value("${app.label}")
    private String appLabel;

    @PostConstruct
    public void Init(){
        log.info("Not Real Controller Loaded appLabel={}, testmode={}", appLabel, testmode);
    }

    @GetMapping("/systeminfo")
    public @ResponseBody String systemInfo(){

        return "Not Real";
    }
}
