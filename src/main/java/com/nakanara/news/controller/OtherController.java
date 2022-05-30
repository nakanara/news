package com.nakanara.news.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * package : com.nakanara.news.controller
 * class : OtherController.java
 * date: 2022-05-31 오전 2:01
 * user : jwpark
 * descr : 환경 변수에 따른 real 아닌 경우 Loading
 * DB 정보, 로그 정보 등, 인터페이스 관련
 *
 **/

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
