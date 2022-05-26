package com.nakanara.news.repogitory;

import com.nakanara.news.entity.Comment;
import com.nakanara.news.entity.News;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepogitory extends CrudRepository<Comment, Long>, JpaSpecificationExecutor {

    List<Comment> findAllByNewsComments(News news);

}
