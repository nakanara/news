package com.nakanara.news.controller;

import com.nakanara.news.entity.News;
import com.nakanara.news.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
@Slf4j
public class NewsRestController {

    @Autowired
    NewsService newsService;

    @GetMapping("")
    public @ResponseBody List<News> getList(){

        return newsService.getList();
    }

    @PostMapping("/post")
    public @ResponseBody
    News post(@RequestBody News news) {

        log.debug("{}", news.toString());

        return newsService.post(news);
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
}
