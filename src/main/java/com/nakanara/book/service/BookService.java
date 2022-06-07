package com.nakanara.book.service;

import com.nakanara.book.entity.Book;
import com.nakanara.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;


    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * 도서 검색
     */

    public List<Book> getBookList(){
        return bookRepository.findAll();
    }

    /**
     * 책 등록/수정
     * @param book
     * @return
     */
    public Book addBook(Book book) {

        bookRepository.save(book);

        return book;
    }

    public Book getBook(long bookId) {
        return bookRepository.getById(bookId);
    }
}
