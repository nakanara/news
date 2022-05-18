package com.nakanara.news.repogitory;

import com.nakanara.news.dto.NewsEntity;
import com.nakanara.news.dto.NewsTagEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NewsTagEntityRepogitory extends CrudRepository<NewsTagEntity, Long> {
    List<NewsEntity> findAll(Sort direct);


}
