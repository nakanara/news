package com.nakanara.news.controller;

import com.nakanara.news.entity.News;
import com.nakanara.news.entity.NewsTag;
import com.nakanara.news.service.JournallistService;
import com.nakanara.news.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/news")
@Slf4j
public class NewsController {


    private NewsService newsService;

    private JournallistService journallistService;


    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @Autowired
    public void setJournallistService(JournallistService journallistService) {
        this.journallistService = journallistService;
    }

    @GetMapping("")
    public String getList(Model model){

        model.addAttribute("list", newsService.getList());

        return "/news/index";
    }


    @GetMapping("/write")
    public String write(Model model){

        model.addAttribute("journalist", journallistService.getList());
        return "/news/write";
    }


    @PostMapping("/write")
    public String doWrite(@ModelAttribute("news") News news) {

        log.debug("{}", news.toString());
        newsService.post(news);

        return "redirect:/news";
    }

    @GetMapping("/{id}")
    public String view(Model model,
                       @PathVariable long id) {


        News news = newsService.view(id);
        model.addAttribute("news", news);
        model.addAttribute("journallistRels", newsService.getNewsJournallist(news));

        return "/news/view";
    }

    @GetMapping("/edit/{id}")
    public String write(Model model,
                        @PathVariable long id){

        News news = newsService.view(id);

        model.addAttribute("news", news);
        return "/news/edit";
    }

    @PutMapping("/{id}")
    public @ResponseBody
    News put(@RequestBody News news) {

        log.debug("{}", news.toString());

        return newsService.post(news);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody String delete(@PathVariable long id) {

        newsService.delete(id);

        return ""+id;
    }

    @GetMapping("/tag/{tag}")
    public @ResponseBody
    List<NewsTag> getTagList(@PathVariable(name = "tag") String tag) {
        return newsService.getTagList(tag);
    }
}
