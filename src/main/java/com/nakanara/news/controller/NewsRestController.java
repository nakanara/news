package com.nakanara.news.controller;

import com.nakanara.news.entity.Comment;
import com.nakanara.news.entity.News;
import com.nakanara.news.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * package : com.nakanara.news.controller
 * class : NewsRestController.java
 * date: 2022-06-01 오후 11:53
 * user : jwpark
 * descr : 뉴스 정보 RESTFul API
 *
 **/

@RestController
@RequestMapping("/api/news")
@Slf4j
public class NewsRestController {

    @Autowired
    NewsService newsService;

    /**
     * 댓글 조회
     * @param newsId
     * @param orderby
     * @return
     */
    @GetMapping("/comments/{newsId}")
    public @ResponseBody List<Comment> getComments(@PathVariable("newsId") long newsId,
                                                   @RequestParam(name = "orderby", defaultValue = "asc") String orderby) {

         return newsService.getCommentList(newsId, orderby);
    }

    /**
     * 댓글 쓰기
     * @param newsId
     * @param comment
     * @param error
     * @return
     */
    @PostMapping("/comment/{newsId}")
    public @ResponseBody List<Comment> saveComment(@PathVariable("newsId") long newsId,
                                                   @RequestBody Comment comment,
                                                   Error error) {

        comment.setNews(newsService.getNews(newsId));
        newsService.saveComment(comment);

        return newsService.getCommentList(newsId);
    }

    /**
     * 뉴스 조회
     * @param keyword
     * @return
     */
    @GetMapping("/search")
    public @ResponseBody List<News> searchNews(@RequestParam(name = "keyword", defaultValue = "") String keyword) {
        return newsService.getSearchNews(keyword);
    }
}
