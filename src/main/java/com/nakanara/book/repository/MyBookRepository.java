package com.nakanara.book.repository;

import com.nakanara.book.entity.Book;
import com.nakanara.book.entity.MyBook;
import com.nakanara.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MyBookRepository extends JpaRepository<MyBook, Long> {

    List<MyBook> findAllByUserEntityIdAndBookId(UserEntity userEntity, Book book);

}
