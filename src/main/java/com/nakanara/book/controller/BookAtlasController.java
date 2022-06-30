package com.nakanara.book.controller;

import com.nakanara.book.entity.Book;
import com.nakanara.book.entity.BookAtlas;
import com.nakanara.book.entity.BookAtlasRel;
import com.nakanara.book.service.BookAtlasService;
import com.nakanara.book.service.BookService;
import com.nakanara.core.service.MemberService;
import com.nakanara.news.entity.News;
import com.nakanara.support.api.service.SearchAladinBookAPI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/bookatlas")
@RequiredArgsConstructor
@Slf4j
public class BookAtlasController {

    private static String PREFIX = "/bookatlas";

    private final BookService bookService;

    private final SearchAladinBookAPI searchAladinBookAPI;

    private final MemberService memberService;

    private final BookAtlasService bookAtlasService;

    @GetMapping("")
    public String getList(Model model,
                          @RequestParam(name = "keyword", defaultValue = "") String keyword,
                          @RequestParam(name = "page", defaultValue = "1") int page,
                          @RequestParam(name = "size", defaultValue = "10") int size){

        model.addAttribute("keyword", keyword);
        model.addAttribute("resultVo", bookAtlasService.getBookAtlasList(keyword, page, size));

        return PREFIX + "/index";
    }

    @GetMapping("/{bookAtlasUid}")
    public String viewBookAtlas(Model model,
                           @PathVariable long bookAtlasUid) {

        BookAtlas bookAtlas = bookAtlasService.getBookAtlas(bookAtlasUid);

        List<BookAtlasRel> bookAtlasRels = bookAtlasService.getBookAtlasRel(bookAtlas);

        model.addAttribute("bookAtlas", bookAtlas);
        model.addAttribute("bookAtlasRels", bookAtlasRels);

        return PREFIX + "/view";
    }



    @GetMapping("/write")
    public String goBookAtlasWrite(Model model){

        return PREFIX +  "/write";
    }

    @PostMapping("/write")
    public String doBookAtlasWrite(Model model,
                                   @ModelAttribute(name = "bookatlas") BookAtlas bookAtlas,
                                   @RequestParam(value = "src_book_id[]") List<Long> srcBookIds){

        List<BookAtlasRel> bookAtlasRels = new ArrayList<>();

        srcBookIds.stream().forEach( s -> {
            bookAtlasRels.add(BookAtlasRel.builder()
                    .book(Book.builder().bookId(s).build())
                    .build());
        });


        bookAtlasService.saveBookAtlas(
                bookAtlas
                , bookAtlasRels
        );

        return "redirect:" + PREFIX;
    }

    @GetMapping("/edit/{bookAtlasUid}")
    public String editNews(Model model,
                           @PathVariable(name = "bookAtlasUid") long bookAtlasUid){

        BookAtlas bookAtlas = bookAtlasService.getBookAtlas(bookAtlasUid);

        List<BookAtlasRel> bookAtlasRels = bookAtlasService.getBookAtlasRel(bookAtlas);

        model.addAttribute("bookAtlas", bookAtlas);
        model.addAttribute("bookAtlasRels", bookAtlasRels);

        return PREFIX + "/edit";
    }

}
