package com.nakanara.news.repogitory;

import com.nakanara.news.entity.Comment;
import com.nakanara.news.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepogitory extends JpaRepository<Comment, Long> {

    // News로 검색하며 정렬을 등록일 기준 내림
    List<Comment> findAllByNewsOrderByRegDttmDesc(News news);

    // News로 검색하며 정렬을 등록일 오름
    List<Comment> findAllByNewsOrderByRegDttmAsc(News news);

}
