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
    public @ResponseBody News getComments(@PathVariable("newsId") long newsId) {

        // return newsService.getCommentList(newsId);
        return newsService.view(newsId);
    }

    public @ResponseBody List<Comment> saveComment(Comment comment) {

        newsService.saveComment(comment);

return null;
        //return newsService.getCommentList(comment.getNews().getNewsId());
    }
}
