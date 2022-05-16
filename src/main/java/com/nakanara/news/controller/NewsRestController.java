package com.nakanara.news.controller;

import com.nakanara.news.dto.TbNews;
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
    public @ResponseBody List<TbNews> getList(){

        return newsService.getList();
    }

    @PostMapping("/post")
    public @ResponseBody TbNews post(@RequestBody TbNews tbNews) {

        log.debug("{}", tbNews.toString());

        return newsService.post(tbNews);
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
