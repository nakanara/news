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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public boolean delete(long id) {

        newsEntityRepogitory.delete(
                NewsEntity.builder().id(id).build()
        );

        return true;
    }
}
