package com.nakanara.news.repository;

import com.nakanara.news.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsRepository extends PagingAndSortingRepository<News, Long> {

    /**
     * 뉴스 전체 검수
     * @param direct
     * @return
     */
    List<News> findAll(Sort direct);

    Page findAll(Pageable pageable);

    /**
     * 제목을 통한 검사
     * @param keyword
     * @return
     */
    List<News> findByTitleContainingIgnoreCase(@Param("news") String keyword);



}
