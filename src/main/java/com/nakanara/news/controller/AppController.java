package com.nakanara.news.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * package : com.nakanara.news.controller
 * class : AppController.java
 * date: 2022-05-31 오전 2:00
 * user : jwpark
 * descr : 초기페이지
 *
 **/

@Controller
@Slf4j
public class AppController {


    @GetMapping("/")
    public String index(){

        return "index";
    }

    @GetMapping("ui/{page}")
    public String goPage(@PathVariable String page) {
        return "/sample/" + page;
    }
}
