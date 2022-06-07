package com.nakanara.book.repository;

import com.nakanara.book.entity.Book;
import com.nakanara.book.entity.BookQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookQuestionRepository extends JpaRepository<BookQuestion, Long> {

    List<BookQuestion> findAllByBookOrderByRecommendDesc(Book book);
}
