package com.nakanara.news.service;

import com.nakanara.news.entity.Comment;
import com.nakanara.news.entity.News;
import com.nakanara.news.entity.NewsJournallistRel;
import com.nakanara.news.entity.NewsTag;
import com.nakanara.news.repository.*;
import com.nakanara.core.vo.ResultVO;
import com.sun.istack.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class NewsService {

    private NewsRepository newsRepository;
    private NewsTagRepository newsTagRepository;
    private CommentRepository commentRepository;
    private NewsJournallistRelRepository newsJournallistRelRepository;
    private JournallistRepository journallistRepository;


    @Autowired
    public void setJournallistRepository(JournallistRepository journallistRepository) {
        this.journallistRepository = journallistRepository;
    }

    @Autowired
    public void setNewsRepository(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Autowired
    public void setNewsTagRepository(NewsTagRepository newsTagRepository) {
        this.newsTagRepository = newsTagRepository;
    }

    @Autowired
    public void setCommentRepository(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Autowired
    public void setNewsJournallistRelRepository(NewsJournallistRelRepository newsJournallistRelRepository) {
        this.newsJournallistRelRepository = newsJournallistRelRepository;
    }

    public News post(@NotNull News news) {


        if(StringUtils.hasLength(news.getTag())) {
            String[] tag_1 = news.getTag().split(",");

            for (String t : tag_1) {
                NewsTag newsTag = new NewsTag();
                newsTag.setTag(StringUtils.trimAllWhitespace(t));
                newsTag.setNews(news);

                newsTagRepository.save(newsTag);
            }
        }


        newsRepository.save(news);

        for(long id: news.getJournallist()) {

            newsJournallistRelRepository.save(
                    NewsJournallistRel.builder()
                    .news(news)
                    .journallist(journallistRepository.findById(id).orElse(null))
                    .build()
            );

        }

        return news;
    }

    public List<News> getList() {
        return getList(Sort.Direction.DESC, "newsId");
    }

    public List<News> getList(Sort.Direction direct, String sort) {


        return newsRepository.findAll(Sort.by(direct, sort));
    }

    public ResultVO getNewsPage(int page, int size){

        Page<News> pagedResult = newsRepository.findAll(PageRequest.of(page-1, size, Sort.by("regDttm").descending()));

        if(pagedResult.hasContent()) {
            return ResultVO.builder().data(pagedResult.getContent())
                    .totalPage(pagedResult.getTotalPages())
                    .curPage(page)
                    .build();

        } else {
            return ResultVO.builder().data(new ArrayList<News>())
                    .totalPage(0)
                    .curPage(0)
                    .build();
        }
    }

    public News view(long id) {

        News news = getNews(id);

        news.setViewCount(news.getViewCount()+1);
        newsRepository.save(news);

        return news;
    }

    public News getNews(long id) {
        return newsRepository.findById(id).orElse(new News());
    }



    @Transactional
    public boolean delete(long id) {

        News news = newsRepository.findById(id).orElse(null);

        newsJournallistRelRepository.deleteAllByNews(news);

        newsRepository.delete(
                News.builder().newsId(id).build()
        );

        return true;
    }

    public boolean saveComment(Comment comment) {

        commentRepository.save(comment);

        return true;
    }

    public List<Comment> getCommentList(long newsId) {

        return getCommentList(newsId, "asc");
    }

    public List<Comment> getCommentList(long newsId, String orderby) {

        if("asc".equalsIgnoreCase(orderby))
            return commentRepository.findAllByNewsOrderByRegDttmAsc(getNews(newsId));
        else {
            return commentRepository.findAllByNewsOrderByRegDttmDesc(getNews(newsId));
        }
    }

/*
    private Specification<Comment> getMultiSpec(Map<String, Object> map) {
        return new Specification<Comment>() {
            @Override
            public Predicate toPredicate(Root<Comment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate p = criteriaBuilder.conjunction();

                p = criteriaBuilder.equal(root.get("newsId"), map.get("newsId"));

                return p;
            }
        };
    }
    */

    public List<NewsTag> getTagList(String tag) {
        return newsTagRepository.findAllByTag(tag);
    }

    public List<News> getSearchNews(String keyword) {
        return newsRepository.findByTitleContainingIgnoreCase(keyword);
    }


    public List<NewsJournallistRel> getNewsJournallist(News news) {
        return newsJournallistRelRepository.getAllByNews(news);
    }
}
