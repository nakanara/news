package com.nakanara.book.repository;

import com.nakanara.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByIsbn13(String isbn13);
}
