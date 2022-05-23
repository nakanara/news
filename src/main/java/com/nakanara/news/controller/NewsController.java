package com.nakanara.news.controller;

import com.nakanara.news.dto.NewsEntity;
import com.nakanara.news.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/news")
@Slf4j
public class NewsController {


    private NewsService newsService;


    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("")
    public String getList(Model model){

        model.addAttribute("list", newsService.getList());

        return "/news/news";
    }

    @GetMapping("/write")
    public String write(Model model){

        return "/news/write";
    }


    @PostMapping("/write")
    public String doWrite(@ModelAttribute("news") NewsEntity newsEntity) {

        log.debug("{}", newsEntity.toString());
        newsService.post(newsEntity);

        // todo 오류.

        return "redirect:/news";
    }

    @GetMapping("/{id}")
    public String view(Model model,
                       @PathVariable long id) {


        NewsEntity newsEntity = newsService.view(id);

        model.addAttribute("news", newsEntity);
        return "/news/view";
    }

    @GetMapping("/edit/{id}")
    public String write(Model model,
                        @PathVariable long id){

        NewsEntity newsEntity = newsService.view(id);

        model.addAttribute("news", newsEntity);
        return "/news/edit";
    }

    @PutMapping("/{id}")
    public @ResponseBody NewsEntity put(@RequestBody NewsEntity newsEntity) {

        log.debug("{}", newsEntity.toString());

        return newsService.post(newsEntity);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody String delete(@PathVariable long id) {

        newsService.delete(id);

        return ""+id;
    }
}
