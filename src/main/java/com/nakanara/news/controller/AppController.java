package com.nakanara.news.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    public String index(Model model){

        model.addAttribute("today", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 E")));
        model.addAttribute("temp", "24°C");

        return "index";
    }

    @GetMapping("ui/{page}")
    public String goPage(@PathVariable String page) {
        return "/sample/" + page;
    }
}
