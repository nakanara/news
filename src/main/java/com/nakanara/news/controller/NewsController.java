package com.nakanara.news.controller;

import com.nakanara.news.entity.News;
import com.nakanara.news.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/news")
@Slf4j
public class NewsController {


    private NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    /**
     * News 조회
     * @param model
     * @return
     */
    @GetMapping("")
    public String getList(Model model){

        model.addAttribute("list", newsService.getList());

        return "/news/index";
    }

    @GetMapping("/write")
    public String write(Model model){

        return "/news/write";
    }


    @PostMapping("/write")
    public String doWrite(@ModelAttribute("news") News news) {

        log.debug("{}", news.toString());
        newsService.post(news);

        // todo 오류.

        return "redirect:/news";
    }

    @GetMapping("/{id}")
    public String view(Model model,
                       @PathVariable long id) {


        News news = newsService.view(id);

        model.addAttribute("news", news);
        return "/news/view";
    }

    @GetMapping("/edit/{id}")
    public String write(Model model,
                        @PathVariable long id){

        News news = newsService.view(id);

        model.addAttribute("news", news);
        return "/news/edit";
    }

    @PutMapping("/{id}")
    public @ResponseBody
    News put(@RequestBody News news) {

        log.debug("{}", news.toString());

        return newsService.post(news);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody String delete(@PathVariable long id) {

        newsService.delete(id);

        return ""+id;
    }

}
