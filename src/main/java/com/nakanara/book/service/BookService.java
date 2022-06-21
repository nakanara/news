package com.nakanara.book.service;

import com.nakanara.book.entity.Book;
import com.nakanara.book.entity.BookQuestion;
import com.nakanara.book.entity.MyBook;
import com.nakanara.book.repository.BookQuestionRepository;
import com.nakanara.book.repository.BookRepository;
import com.nakanara.book.repository.MyBookRepository;
import com.nakanara.core.vo.ResultVO;
import com.nakanara.support.api.service.vo.AladinResultItemVO;
import com.nakanara.support.api.service.vo.AladinResultVO;
import com.nakanara.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookQuestionRepository bookQuestionRepository;
    private final MyBookRepository myBookRepository;


    /**
     * 도서 검색
     */

    public List<Book> getBookList(){
        return bookRepository.findAll();
    }

    public ResultVO getBookPage(int page, int size){

        Page<Book> pagedResult = bookRepository.findAll(PageRequest.of(page-1, size, Sort.by("title").ascending()));

        log.info("{}", pagedResult.toString());

        if(pagedResult.hasContent()) {
            return ResultVO.builder().data(pagedResult.getContent())
                    .totalPage(pagedResult.getTotalPages())
                    .curPage(page)
                    .build();

        } else {
            return ResultVO.builder().data(new ArrayList<>())
                    .totalPage(0)
                    .curPage(0)
                    .build();
        }


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

    public Book getBookIsbn13(String isbn13) {
        return bookRepository.findByIsbn13(isbn13);
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


    /**
     * 도서 상태
     * @param userEntity
     * @param book
     * @return
     */
    public MyBook getMyBook(UserEntity userEntity, Book book) {
        List<MyBook> myBooks = myBookRepository.findAllByUserEntityIdAndBookId(userEntity, book);

        MyBook myBook = null;
        if(myBooks.isEmpty()){
            myBook = new MyBook();
        }else {
            myBook = myBooks.get(0);
        }

        return myBook;
    }
    /**
     * 책 좋아요 업데이트
     * @param isbn13
     * @return
     */
    @Transactional
    public long likeBook(UserEntity userEntity, String isbn13, boolean bookLike) {
        Book book = getBookIsbn13(isbn13);

        List<MyBook> myBooks = myBookRepository.findAllByUserEntityIdAndBookId(userEntity, book);

        MyBook myBook;
        if(myBooks.isEmpty()) {
            myBook = MyBook.builder()
                    .userEntityId(userEntity)
                    .bookId(book)
                    .build();
        } else {
            myBook = myBooks.get(0);
        }

        myBook.setBookLike(bookLike);

        long likeCount = book.getLikeCount();

        if(bookLike) {
            likeCount++;
        } else {
            likeCount--;
        }

        book.setLikeCount(likeCount);

        myBookRepository.save(myBook);
        bookRepository.save(book);

        return likeCount;

    }

}
