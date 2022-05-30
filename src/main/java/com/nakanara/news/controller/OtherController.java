package com.nakanara.news.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@Slf4j
@RestController
@Profile("!real")
public class OtherController {

    @PostConstruct
    public void Init(){
        log.info("Other Controller Loaded profile={}");
    }

    @GetMapping("/dev/info")
    public @ResponseBody String systemInfo(){

        return "Dev Mode";
    }
}
