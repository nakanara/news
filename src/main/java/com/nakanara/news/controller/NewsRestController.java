package com.nakanara.news.controller;

import com.nakanara.news.dto.NewsEntity;
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
    public @ResponseBody List<NewsEntity> getList(){

        return newsService.getList();
    }

    @PostMapping("/post")
    public @ResponseBody NewsEntity post(@RequestBody NewsEntity newsEntity) {

        log.debug("{}", newsEntity.toString());

        return newsService.post(newsEntity);
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
