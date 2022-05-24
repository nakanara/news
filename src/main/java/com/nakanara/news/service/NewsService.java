package com.nakanara.news.service;

import com.nakanara.news.entity.News;
import com.nakanara.news.entity.NewsTag;
import com.nakanara.news.repogitory.NewsEntityRepogitory;
import com.nakanara.news.repogitory.NewsTagEntityRepogitory;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class NewsService {

    private NewsEntityRepogitory newsEntityRepogitory;
    private NewsTagEntityRepogitory newsTagEntityRepogitory;


    @Autowired
    public void setNewsEntityRepogitory(NewsEntityRepogitory newsEntityRepogitory) {
        this.newsEntityRepogitory = newsEntityRepogitory;
    }

    @Autowired
    public void setNewsTagEntityRepogitory(NewsTagEntityRepogitory newsTagEntityRepogitory) {
        this.newsTagEntityRepogitory = newsTagEntityRepogitory;
    }

    @Transactional
    public News post(@NotNull News news) {


        if(StringUtils.hasLength(news.getTag())) {
            String[] tag_1 = news.getTag().split(",");

            for (String t : tag_1) {
                NewsTag newsTag = new NewsTag();
                newsTag.setTag(t);
                newsTag.setNews(news);

                newsTagEntityRepogitory.save(newsTag);
            }
        }

        newsEntityRepogitory.save(news);

        return news;
    }

    public List<News> getList() {
        return getList(Sort.Direction.DESC, "newsId");
    }

    public List<News> getList(Sort.Direction direct, String sort) {

        return newsEntityRepogitory.findAll(Sort.by(direct, sort));
    }

    public News view(long id) {

        News news = newsEntityRepogitory.findById(id).orElse(null);

        news.setViewCount(news.getViewCount()+1);
        newsEntityRepogitory.save(news);

        return news;
    }



    public boolean delete(long id) {

        newsEntityRepogitory.delete(
                News.builder().newsId(id).build()
        );

        return true;
    }
}
