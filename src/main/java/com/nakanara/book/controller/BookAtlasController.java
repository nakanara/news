package com.nakanara.book.controller;

import com.google.gson.JsonObject;
import com.nakanara.book.entity.Book;
import com.nakanara.book.entity.BookQuestion;
import com.nakanara.book.entity.MyBook;
import com.nakanara.book.service.BookService;
import com.nakanara.core.annotation.ApiInfo;
import com.nakanara.core.service.MemberService;
import com.nakanara.support.api.service.SearchAladinBookAPI;
import com.nakanara.support.api.service.vo.AladinResultVO;
import com.nakanara.user.entity.UserEntity;
import com.nakanara.util.http.HttpRequestUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/bookatlas")
@RequiredArgsConstructor
@Slf4j
public class BookAtlasController {

    private static String PREFIX = "/book/atlas";

    private final BookService bookService;

    private final SearchAladinBookAPI searchAladinBookAPI;

    private final MemberService memberService;

    @GetMapping("/write")
    public String goBookAtlasWrite(Model model){

        return PREFIX +  "/write";
    }

    @PostMapping("/write")
    public String goBookAtlasWrite(Model model,
                                   @RequestBody Map map){

        log.info("{}", map);
        return PREFIX +  "/write";
    }




}
