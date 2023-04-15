package com.nakanara.news.controller;

import com.nakanara.news.entity.Comment;
import com.nakanara.news.entity.News;
import com.nakanara.news.entity.NewsTag;
import com.nakanara.news.service.JournallistService;
import com.nakanara.news.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * package : com.nakanara.news.controller
 * class : NewsController.java
 * date: 2022-06-01 오후 11:50
 * user : jwpark
 * descr :
 *  뉴스 서비스
 **/

@Controller
@RequestMapping("/news")
@Slf4j
public class NewsController {

    private static final String PRE_FIX = "/news";

    /**
     * 뉴스 조회
     */
    private NewsService newsService;

    /**
     * 기자 조회
     */
    private JournallistService journallistService;


    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @Autowired
    public void setJournallistService(JournallistService journallistService) {
        this.journallistService = journallistService;
    }


    @GetMapping("")
    public String getList(Model model,
                          @RequestParam(name = "page", defaultValue = "1") int page,
                          @RequestParam(name = "size", defaultValue = "10") int size){


        model.addAttribute("resultVo", newsService.getNewsPage(page, size));

        return "/news/index";
    }


    @GetMapping("/write")
    public String goNewsWrite(Model model){
        model.addAttribute("journalist", journallistService.getList());
        return "/news/write";
    }


    @PostMapping("/write")
    public String saveNews(@ModelAttribute("news") News news) {

        log.debug("{}", news.toString());
        newsService.post(news);

        return "redirect:/news";
    }

    @RequestMapping(value = "/uploadImage.do", method = RequestMethod.POST)
    public String writeTestPost(@RequestParam("image") MultipartFile multi,
                                HttpServletRequest request, HttpServletResponse response) {

        String url = null;
//        ModelAndView mv = new ModelAndView();
//
//        try {
//            String uploadPath = path;
//            String originFilename = multi.getOriginalFilename();
//            String extName = originFilename.substring(originFilename.lastIndexOf("."), originFilename.length());
//            long size = multi.getSize();
//            FileNameModel fileNameModel = new FileNameModel();
//            String saveFileName = fileNameModel.GenSaveFileName(extName);
//
//            if(!multi.isEmpty()) {
//                File file = new File(uploadPath, saveFileName);
//                multi.transferTo(file);
//
//                mv.addObject("filename", saveFileName);
//                mv.addObject("uploadPath", file.getAbsolutePath());
//                mv.addObject("url", uploadPath+saveFileName);
//                System.out.println("url : " + uploadPath+saveFileName);
//
//                mv.setViewName("image_Url_Json");
//            } else {
//                mv.setViewName("toast_UI_writer3");
//            }
//        } catch (Exception e) {
//            // TODO: handle exception
//            System.out.println("[Error] " + e.getMessage());
//        }
//        return mv;

        return "";
    }

    @GetMapping("/{id}")
    public String viewNews(Model model,
                       @PathVariable long id) {


        News news = newsService.view(id);
        model.addAttribute("news", news);
        model.addAttribute("journallistRels", newsService.getNewsJournallist(news));
        model.addAttribute("comments", newsService.getCommentList(id));

        return "/news/view";
    }

    @GetMapping("/edit/{id}")
    public String editNews(Model model,
                        @PathVariable long id){

        News news = newsService.view(id);

        model.addAttribute("news", news);
        return "/news/edit";
    }

    @PutMapping("/{id}")
    public @ResponseBody
    News updateNews(@RequestBody News news) {

        log.debug("{}", news.toString());

        return newsService.post(news);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody String deleteNews(@PathVariable long id) {

        newsService.delete(id);

        return ""+id;
    }



    @GetMapping("/tag/{tag}")
    public @ResponseBody
    List<NewsTag> getTagList(@PathVariable(name = "tag") String tag) {
        return newsService.getTagList(tag);
    }

    /**
     * 댓글 쓰기
     * @param newsId
     * @param comment
     * @param error
     * @return
     */
    @PostMapping("/{newsId}/comment")
    public String saveNewsComment(Model model,
                                  @PathVariable("newsId") long newsId,
                                  @RequestBody Comment comment,
                                  Error error) {

        comment.setNews(newsService.getNews(newsId));
        newsService.saveComment(comment);

        model.addAttribute("comments", newsService.getCommentList(newsId));

        return PRE_FIX + "/view :: #commentTable";
    }
}
