package com.nakanara.news.repository;

import com.nakanara.news.entity.Comment;
import com.nakanara.news.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByNews(News news);

    List<Comment> findAllByNewsOrderByRegDttmDesc(News news);

    List<Comment> findAllByNewsOrderByRegDttmAsc(News news);

}
