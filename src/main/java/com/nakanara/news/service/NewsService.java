package com.nakanara.news.service;

import com.nakanara.news.entity.Comment;
import com.nakanara.news.entity.News;
import com.nakanara.news.entity.NewsTag;
import com.nakanara.news.repogitory.CommentRepogitory;
import com.nakanara.news.repogitory.NewsRepogitory;
import com.nakanara.news.repogitory.NewsTagRepogitory;
import com.sun.istack.NotNull;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class NewsService {

    private NewsRepogitory newsRepogitory;
    private NewsTagRepogitory newsTagRepogitory;

    private CommentRepogitory commentRepogitory;


    @Autowired
    public void setNewsRepogitory(NewsRepogitory newsRepogitory) {
        this.newsRepogitory = newsRepogitory;
    }

    @Autowired
    public void setNewsTagRepogitory(NewsTagRepogitory newsTagRepogitory) {
        this.newsTagRepogitory = newsTagRepogitory;
    }

    @Autowired
    public void setCommentRepogitory(CommentRepogitory commentRepogitory) {
        this.commentRepogitory = commentRepogitory;
    }

    @Transactional
    public News post(@NotNull News news) {


        if(StringUtils.hasLength(news.getTag())) {
            String[] tag_1 = news.getTag().split(",");

            for (String t : tag_1) {
                NewsTag newsTag = new NewsTag();
                newsTag.setTag(StringUtils.trimAllWhitespace(t));
                newsTag.setNews(news);

                newsTagRepogitory.save(newsTag);
            }
        }

        newsRepogitory.save(news);

        return news;
    }

    public List<News> getList() {
        return getList(Sort.Direction.DESC, "newsId");
    }

    public List<News> getList(Sort.Direction direct, String sort) {

        return newsRepogitory.findAll(Sort.by(direct, sort));
    }

    public News view(long id) {

        News news = getNews(id);

        news.setViewCount(news.getViewCount()+1);
        newsRepogitory.save(news);

        return news;
    }

    public News getNews(long id) {
        return newsRepogitory.findById(id).orElse(new News());
    }



    public boolean delete(long id) {

        newsRepogitory.delete(
                News.builder().newsId(id).build()
        );

        return true;
    }

    public boolean saveComment(Comment comment) {

        commentRepogitory.save(comment);

        return true;
    }

    public List<Comment> getCommentList(long newsId) {

        return getCommentList(newsId, "asc");
    }

    public List<Comment> getCommentList(long newsId, String orderby) {

        if("asc".equalsIgnoreCase(orderby))
            return commentRepogitory.findAllByNewsOrderByRegDttmAsc(getNews(newsId));
        else {
            return commentRepogitory.findAllByNewsOrderByRegDttmDesc(getNews(newsId));
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
        return newsTagRepogitory.findAllByTag(tag);
    }

    public void getTagcount(){

//        JPAQueryFactory qf = new JPAQueryFactory(entityManager);
//
//        JPAQuery<YearReportSum> query = qf.from(qReport)
//                .groupBy(qReport.year)
//                .select(
//                        Projections.bean(
//                                YearReportSum.class,
//                                qReport.year,
//                                qReport.loanSmall.sum().as("smallSum"),
//                                qReport.loanMajor.sum().as("majorSum"),
//                                qReport.loanTotal.sum().as("totalSum")
//                        )
//                );
//        return query.fetch();
    }
}
