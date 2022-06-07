package com.nakanara.book.controller;

import com.nakanara.book.entity.Book;
import com.nakanara.book.entity.BookQuestion;
import com.nakanara.book.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/book")
@Slf4j
public class BookController {

    private static String PREFIX = "/book";
    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("")
    public String getList(Model model,
                          @RequestParam(name = "page", defaultValue = "1") int page,
                          @RequestParam(name = "size", defaultValue = "10") int size){


        model.addAttribute("resultVo", bookService.getBookList());

        return PREFIX + "/index";
    }


    @GetMapping("/write")
    public String goNewsWrite(Model model){

        return PREFIX +  "/write";
    }


    @PostMapping("/write")
    public String saveNews(@ModelAttribute("book") Book book) {

        log.debug("{}", book.toString());
        bookService.addBook(book);

        return "redirect:" + PREFIX;
    }

    @GetMapping("/{id}")
    public String viewNews(Model model,
                           @PathVariable long id) {


        Book book = bookService.getBook(id);
        model.addAttribute("book", book);
        model.addAttribute("questions", bookService.getQuestion(book));

        return PREFIX + "/view";
    }

    @GetMapping("/{id}/question")
    public String viewQuestion(Model model,
                               @PathVariable long id){


        Book book = bookService.getBook(id);
        model.addAttribute("book", book);


        return PREFIX + "/question";
    }

    /**
     * 질문 등록
     * @param model
     * @param bookId
     * @param bookQuestion
     * @param error
     * @return
     */
    @PostMapping("/{bookId}/question")
    public String addBookQuestion(Model model,
                                  @PathVariable("bookId") long bookId,
                                  @RequestBody BookQuestion bookQuestion,
                                  Error error) {


        Book book = bookService.getBook(bookId);

        bookQuestion.setBook(book);
        bookService.addQuestion(bookQuestion);
        model.addAttribute("questions", bookService.getQuestion(book));

        return PREFIX + "/view :: #questionTable";
    }

    @PostMapping("/{bookId}/like/{bookQuestionId}")
    public String addQuestionRecommend(Model model,
                                  @PathVariable("bookId") long bookId,
                                  @PathVariable("bookQuestionId") long bookQuestionId,
                                  Error error) {


        Book book = bookService.getBook(bookId);

        bookService.addQuestionRecommend(bookQuestionId);

        model.addAttribute("questions", bookService.getQuestion(book));

        return PREFIX + "/view :: #questionTable";
    }
}
