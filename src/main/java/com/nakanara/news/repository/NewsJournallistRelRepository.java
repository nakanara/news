package com.nakanara.news.repository;

import com.nakanara.news.entity.News;
import com.nakanara.news.entity.NewsJournallistRel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsJournallistRelRepository extends CrudRepository<NewsJournallistRel, Long> {

    List<NewsJournallistRel> getAllByNews(@Param("news") News news);

    int deleteAllByNews(News news);
}
