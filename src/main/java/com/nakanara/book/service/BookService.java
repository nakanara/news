package com.nakanara.book.service;

import com.nakanara.book.entity.Book;
import com.nakanara.book.entity.BookQuestion;
import com.nakanara.book.repository.BookQuestionRepository;
import com.nakanara.book.repository.BookRepository;
import com.nakanara.support.api.service.vo.AladinResultItemVO;
import com.nakanara.support.api.service.vo.AladinResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;
    private BookQuestionRepository bookQuestionRepository;


    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Autowired
    public void setBookQuestionRepository(BookQuestionRepository bookQuestionRepository) {
        this.bookQuestionRepository = bookQuestionRepository;
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

    public BookQuestion addQuestion(BookQuestion bookQuestion) {
        return bookQuestionRepository.save(bookQuestion);
    }

    public List<BookQuestion> getQuestion(Book book) {
        return bookQuestionRepository.findAllByBookOrderByRecommendDesc(book);
    }

    public void addQuestionRecommend(long bookQuestionId) {
        BookQuestion bookQuestion = bookQuestionRepository.findById(bookQuestionId).orElse(null);

        if(bookQuestion != null) {
            bookQuestion.addRecommend();
            bookQuestionRepository.save(bookQuestion);
        }
    }

    @Transactional
    public void addBookResult(AladinResultVO aladinResultVO) {
        List<AladinResultItemVO> aladinResultItemVOS = aladinResultVO.getItem();

        for(AladinResultItemVO itemVO : aladinResultItemVOS) {


            Book book = Book.convertAladinItem(itemVO);

            Book searchBook = bookRepository.findByIsbn13(book.getIsbn13());
            if( searchBook == null ) {
                searchBook = bookRepository.save(book);
            }

            itemVO.setBookId(searchBook.getBookId());

        }
    }
}
