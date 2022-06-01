package com.nakanara.news.controller;

import com.nakanara.news.entity.Journallist;
import com.nakanara.news.service.JournallistService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * package : com.nakanara.news.controller
 * class : JournallistController.java
 * date: 2022-06-01 오후 11:54
 * user : jwpark
 * descr : 기자 조회
 *
 **/

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

    /**
     * 기자 리스트 Main
     * @param model
     * @return
     */
    @GetMapping("")
    public String getHome(Model model){

        model.addAttribute("list", journallistService.getList());
        return DEF_PREFIX + "/index";
    }


    @GetMapping("/write")
    public String goJournallistWrite(Model model){

        return DEF_PREFIX + "/write";
    }

    @PostMapping("/write")
    public String saveJournallist(@ModelAttribute("journallist") Journallist journallist) {

        log.debug("{}", journallist.toString());
        journallistService.post(journallist);

        // todo 오류.

        return "redirect:" + DEF_PREFIX;
    }

    @GetMapping("/{id}")
    public String saveJournallist(Model model,
                       @PathVariable long id) {

        Journallist journallist = journallistService.view(id);

        model.addAttribute("journallist", journallist);
        return DEF_PREFIX + "/view";
    }
}
