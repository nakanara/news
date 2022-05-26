package com.nakanara.news.controller;

import com.nakanara.news.entity.Journallist;
import com.nakanara.news.service.JournallistService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/journallist")
public class JournallistController {

    final static String DEF_PREFIX = "/journallist";

    private JournallistService journallistService;

    @Autowired
    public void setJournallistService(JournallistService journallistService) {
        this.journallistService = journallistService;
    }

    @GetMapping("")
    public String getHome(Model model){

        model.addAttribute("list", journallistService.getList());
        return DEF_PREFIX + "/index";
    }


    @GetMapping("/write")
    public String write(Model model){

        return DEF_PREFIX + "/write";
    }

    @PostMapping("/write")
    public String doWrite(@ModelAttribute("journallist") Journallist journallist) {

        log.debug("{}", journallist.toString());
        journallistService.post(journallist);

        // todo 오류.

        return "redirect:" + DEF_PREFIX;
    }

    @GetMapping("/{id}")
    public String view(Model model,
                       @PathVariable long id) {


        Journallist journallist = journallistService.view(id);

        model.addAttribute("journallist", journallist);
        return DEF_PREFIX + "/view";
    }
}
