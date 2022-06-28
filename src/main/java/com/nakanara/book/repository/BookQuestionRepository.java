package com.nakanara.book.repository;

import com.nakanara.book.entity.Book;
import com.nakanara.book.entity.BookQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookQuestionRepository extends JpaRepository<BookQuestion, Long> {

    /**
     * 질문에 대한 추천 순
     * @param book
     * @return
     */
    List<BookQuestion> findAllByBookOrderByRecommendDesc(Book book);

    /**
     * 책과, 페이지를 통한 조회
     * @param book
     * @param page
     * @return
     */
    List<BookQuestion> findAllByBookAndPageGreaterThanOrderByRecommendDesc(Book book, long page);
}
