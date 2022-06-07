package com.nakanara.news.repository;

import com.nakanara.news.entity.News;
import com.nakanara.news.entity.NewsTag;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NewsTagRepository extends CrudRepository<NewsTag, Long> {
    List<News> findAll(Sort direct);

    List<NewsTag> findAllByTag(String tag);

}
