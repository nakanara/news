package com.nakanara.book.entity;

import com.nakanara.user.entity.User;
import lombok.Data;

import javax.persistence.*;

/**
 * package : com.nakanara.book.entity
 * class : BookQuestionRecommend.java
 * date: 2022-06-07 오후 3:04
 * user : jwpark
 * descr : 질문 추천
 *
 **/

@Data
@Entity(name = "BOOK_QUESTION_RECOMMEND")
public class BookQuestionRecommend {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookRecommendUserId;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private BookQuestion bookQuestion;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
