package com.nakanara.news.controller;

import com.nakanara.news.entity.Comment;
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


    @GetMapping("/comments/{newsId}")
    public @ResponseBody List<Comment> getComments(@PathVariable("newsId") long newsId,
                                                   @RequestParam(name = "orderby", defaultValue = "asc") String orderby) {

         return newsService.getCommentList(newsId, orderby);
    }

    @PostMapping("/comment/{newsId}")
    public @ResponseBody List<Comment> saveComment(@PathVariable("newsId") long newsId,
                                                   @RequestBody Comment comment,
                                                   Error error) {

        log.error(error.getMessage());
        comment.setNews(newsService.getNews(newsId));

        newsService.saveComment(comment);

        return newsService.getCommentList(newsId);
    }
}
