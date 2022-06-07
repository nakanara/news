package com.nakanara.book.controller;

import com.nakanara.book.entity.Book;
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
}
