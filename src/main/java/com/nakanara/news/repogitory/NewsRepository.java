package com.nakanara.news.repogitory;

import com.nakanara.news.entity.News;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsRepository extends CrudRepository<News, Long> {
    List<News> findAll(Sort direct);

    List<News> findByTitleContainingIgnoreCase(@Param("news") String keyword);
}
