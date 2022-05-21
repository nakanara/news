package com.nakanara.news.service;

import com.nakanara.news.dto.NewsEntity;
import com.nakanara.news.dto.NewsTagEntity;
import com.nakanara.news.repogitory.NewsEntityRepogitory;
import com.nakanara.news.repogitory.NewsTagEntityRepogitory;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
    public NewsEntity post(@NotNull NewsEntity newsEntity) {


        String[] tag_1 = newsEntity.getTag().split(",");

        for(String t : tag_1) {
            NewsTagEntity newsTagEntity = new NewsTagEntity();
            newsTagEntity.setTag(t);
            newsTagEntity.setNewsEntity(newsEntity);

            newsTagEntityRepogitory.save(newsTagEntity);
        }

        newsEntityRepogitory.save(newsEntity);

        return newsEntity;
    }

    public List<NewsEntity> getList() {
        return getList(Sort.Direction.DESC, "id");
    }

    public List<NewsEntity> getList(Sort.Direction direct, String sort) {

        return newsEntityRepogitory.findAll(Sort.by(direct, sort));
    }

    public NewsEntity view(long id) {

        NewsEntity newsEntity = newsEntityRepogitory.findById(id).orElse(null);

        newsEntity.setViewCount(newsEntity.getViewCount()+1);
        newsEntityRepogitory.save(newsEntity);

        return newsEntity;
    }



    public boolean delete(long id) {

        newsEntityRepogitory.delete(
                NewsEntity.builder().id(id).build()
        );

        return true;
    }
}
