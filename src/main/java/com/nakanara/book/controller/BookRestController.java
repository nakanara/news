package com.nakanara.book.controller;

import com.google.gson.JsonObject;
import com.nakanara.book.entity.Book;
import com.nakanara.book.entity.BookQuestion;
import com.nakanara.book.service.BookService;
import com.nakanara.core.annotation.ApiInfo;
import com.nakanara.core.config.ResultCode;
import com.nakanara.core.service.MemberService;
import com.nakanara.core.vo.ResultVO;
import com.nakanara.support.api.service.SearchAladinBookAPI;
import com.nakanara.support.api.service.vo.AladinResultVO;
import com.nakanara.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Member;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/book")
@Slf4j
public class BookRestController {


    private final BookService bookService;

    private final SearchAladinBookAPI searchAladinBookAPI;

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


    @RequestMapping("/search")
    @ApiInfo(name = "알라딘 도서 검색")
    public ResultVO getSearchBook(Model model,
                                @RequestParam(name = "keyword", required = false) String keyword,
                                @RequestParam(name = "start", defaultValue = "1") String start){

        AladinResultVO aladinResultVO = null;

        if(StringUtils.hasLength(keyword)) {
            aladinResultVO = searchAladinBookAPI.searchBook(keyword, start);
            bookService.addBookResult(aladinResultVO);
        } else {
            aladinResultVO = new AladinResultVO();
        }

        return ResultVO.builder()
                .data(aladinResultVO)
                .code(ResultCode.SUCCESS)
                .build();
    }


    /**
     * 질문 등록
     * @param model
     * @param bookId
     * @param page
     * @param error
     * @return
     */
    @GetMapping("/{bookId}/question/{page}")
    public ResultVO addBookQuestion(Model model,
                                  @PathVariable(name = "bookId", required = true) long bookId,
                                  @PathVariable(name = "page", required = false, value = "0") long page,
                                  Error error) {


        Book book = bookService.getBook(bookId);
        List<BookQuestion> bookQuestions = bookService.getQuestion(book, page);

        return ResultVO.builder()
                .data(bookQuestions)
                .code(ResultCode.SUCCESS)
                .build();

    }


}
