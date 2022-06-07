package com.nakanara.book.entity;

import com.nakanara.core.entity.BaseEntity;

import javax.persistence.*;

/**
 * package : com.nakanara.book.entity
 * class : BookQuestion.java
 * date: 2022-06-07 오후 12:28
 * user : jwpark
 * descr : 도서에 관련된 질문
 *
 **/

@Entity(name = "BOOK_QUESTION")
public class BookQuestion extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookQuestionId;

    /**
     * 질문
     */
    @Column(length = 4000)
    private String question;

    /**
     * 추천
     */
    private int recommend = 0;
}
