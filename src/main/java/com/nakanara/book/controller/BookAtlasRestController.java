package com.nakanara.book.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonObject;
import com.nakanara.book.entity.Book;
import com.nakanara.book.entity.BookAtlas;
import com.nakanara.book.entity.BookAtlasRel;
import com.nakanara.book.service.BookAtlasService;
import com.nakanara.book.service.BookService;
import com.nakanara.core.service.MemberService;
import com.nakanara.core.vo.ResultVO;
import com.nakanara.support.api.service.SearchAladinBookAPI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
    public ResultVO doBookAtlasWrite(Model model,
                                   @RequestBody JsonNode jsonNode){

        log.info("{}", jsonNode);

        Iterator<JsonNode> it = jsonNode.get("bookAtlasRel").iterator();

        List<BookAtlasRel> bookAtlasRels = new ArrayList<>();

        while(it.hasNext()) {
            long bookId = it.next().get("bookId").asLong();

            bookAtlasRels.add(BookAtlasRel.builder()
                    .book(Book.builder().bookId(bookId).build())
                    .build());
        }

        bookAtlasService.saveBookAtlas(
                BookAtlas.builder()
                        .batTitle(jsonNode.get("batTitle").asText())
                        .batDescr(jsonNode.get("batDescr").asText())
                        .succCount(jsonNode.get("succCount").asLong())
                        .limitCount(jsonNode.get("limitCount").asLong())
                        .bookSize(jsonNode.get("bookSize").asInt())
                        .build()
                , bookAtlasRels
        );
        return ResultVO.builder().build();
    }

    @DeleteMapping("/{bookAtlasUid}")
    public @ResponseBody ResultVO deleteAtlas(@PathVariable long bookAtlasUid) {

        bookAtlasService.deleteAtlas(bookAtlasUid);

        return ResultVO.builder().build();

    }



}
