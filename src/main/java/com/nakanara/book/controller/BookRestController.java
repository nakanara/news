package com.nakanara.book.controller;

import com.google.gson.JsonObject;
import com.nakanara.book.service.BookService;
import com.nakanara.core.annotation.ApiInfo;
import com.nakanara.core.config.ResultCode;
import com.nakanara.core.service.MemberService;
import com.nakanara.core.vo.ResultVO;
import com.nakanara.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Member;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/book")
@Slf4j
public class BookRestController {


    private final BookService bookService;

    private final MemberService memberService;

    @PostMapping("/like/{isbn13}")
    @ApiInfo("도서 좋아요")
    public ResultVO bookLike(HttpServletRequest request,
                             HttpSession session,
                             @PathVariable(name = "isbn13", required = true) String isbn13,
                             @RequestBody Map paramMap) {

        log.info("{}", session.getAttribute("user"));
        UserEntity userEntity = (UserEntity)session.getAttribute("user");

        if(userEntity == null) {
            return ResultVO.builder()
                    .code(ResultCode.GUEST)
                    .build();
        }

        UserEntity u = memberService.getUser(userEntity.getUserUid());

        boolean likeBook = (boolean)paramMap.get("likeBook");
        long likeCount = bookService.likeBook(u, isbn13, likeBook);

        return ResultVO.builder()
                .code(ResultCode.SUCCESS)
                .data(likeCount)
                .build();

    }

}
