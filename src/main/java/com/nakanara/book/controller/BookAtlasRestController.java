package com.nakanara.book.controller;

import com.google.gson.JsonObject;
import com.nakanara.book.service.BookAtlasService;
import com.nakanara.book.service.BookService;
import com.nakanara.core.service.MemberService;
import com.nakanara.core.vo.ResultVO;
import com.nakanara.support.api.service.SearchAladinBookAPI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookatlas")
@RequiredArgsConstructor
@Slf4j
public class BookAtlasRestController {

    private static String PREFIX = "/bookatlas";

    private final BookService bookService;

    private final SearchAladinBookAPI searchAladinBookAPI;

    private final MemberService memberService;

    private final BookAtlasService bookAtlasService;


    @RequestMapping("/getList")
    public ResultVO getList(Model model,
                            @RequestParam(name = "page", defaultValue = "1") int page,
                            @RequestParam(name = "size", defaultValue = "10") int size){

        return bookAtlasService.getBookAtlasList(page, size);
    }


    @PostMapping("/write")
    public String goBookAtlasWrite(Model model,
                                   @RequestBody JsonObject jsonObject){

        log.info("{}", jsonObject);
        return PREFIX +  "/write";
    }




}
