package com.nakanara.book.controller;

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
import com.nakanara.util.http.HttpUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private static String PREFIX = "/book";
    private final BookService bookService;

    private final SearchAladinBookAPI searchAladinBookAPI;

    private final MemberService memberService;


    @GetMapping("")
    public String getList(Model model,
                          @RequestParam(name = "page", defaultValue = "1") int page,
                          @RequestParam(name = "size", defaultValue = "10") int size){


        model.addAttribute("resultVo", bookService.getBookPage(page, size));

        return PREFIX + "/index";
    }


    @GetMapping("/write")
    public String goBookWrite(Model model){

        return PREFIX +  "/write";
    }


    @PostMapping("/write")
    public String saveBook(@ModelAttribute("book") Book book) {

        log.debug("{}", book.toString());
        bookService.addBook(book);

        return "redirect:" + PREFIX;
    }

    @GetMapping("/{isbn13}")
    @ApiInfo(name = "책 ")
    public String viewBook(Model model,
                           HttpServletRequest request,
                           @PathVariable(name = "isbn13") String isbn13) {


        Book book = bookService.getBookIsbn13(isbn13);

        // 사용자가 없다면.
        UserEntity userEntity = HttpRequestUtil.getUser(request);
        MyBook myBook = null;

        if(userEntity != null) {
            UserEntity u = memberService.getUser(userEntity.getUserUid());
            myBook = bookService.getMyBook(u, book);
        } else {
            myBook = new MyBook();
        }

        model.addAttribute("myBook", myBook);
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

    @RequestMapping("/search")
    public String getSearchBook(Model model,
                                @RequestParam(name = "keyword", required = false) String keyword,
                                @RequestParam(name = "start", defaultValue = "1") String start){


        model.addAttribute("keyword", keyword);

        if(StringUtils.hasLength(keyword)) {
            AladinResultVO aladinResultVO = searchAladinBookAPI.searchBook(keyword, start);
            bookService.addBookResult(aladinResultVO);
            model.addAttribute("result", aladinResultVO);
        } else {
            model.addAttribute("result", new AladinResultVO());
        }

        return PREFIX + "/popup/search";
    }

    @RequestMapping("/read/{isbn13}")
    @ApiInfo(name = "책 읽기 페이지 전환")
    public String goReadBook(Model model,
                             HttpServletRequest request,
                             @PathVariable(name = "isbn13") String isbn13){

        Book book = bookService.getBookIsbn13(isbn13);

        // 사용자가 없다면.
        UserEntity userEntity = HttpRequestUtil.getUser(request);
        MyBook myBook = null;

        if(userEntity != null) {
            UserEntity u = memberService.getUser(userEntity.getUserUid());
            myBook = bookService.getMyBook(u, book);
        } else {
            myBook = new MyBook();
        }

        model.addAttribute("myBook", myBook);
        model.addAttribute("book", book);


        return PREFIX + "/readBook";
    }

}
