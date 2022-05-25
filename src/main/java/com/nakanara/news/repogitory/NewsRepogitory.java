package com.nakanara.news.repogitory;

import com.nakanara.news.entity.News;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface NewsRepogitory extends CrudRepository<News, Long> {
    List<News> findAll(Sort direct);



}
