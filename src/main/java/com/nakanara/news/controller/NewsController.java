package com.nakanara.news.controller;

import com.nakanara.news.dto.TbNews;
import com.nakanara.news.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String doWrite(@ModelAttribute("news") TbNews tbNews) {

        log.debug("{}", tbNews.toString());
        newsService.post(tbNews);

        // todo 오류.

        return "redirect:/news";
    }

    @PutMapping("/{id}")
    public @ResponseBody TbNews put(@RequestBody TbNews tbNews) {

        log.debug("{}", tbNews.toString());

        return newsService.post(tbNews);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody String delete(@PathVariable long id) {

        newsService.delete(id);

        return ""+id;
    }
}
