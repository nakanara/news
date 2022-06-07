package com.nakanara.book.entity;

import com.nakanara.core.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;

/**
 * package : com.nakanara.book.entity
 * class : BookQuestion.java
 * date: 2022-06-07 오후 12:28
 * user : jwpark
 * descr : 도서에 관련된 질문
 *
 **/

@Data
@Entity(name = "BOOK_QUESTION")
public class BookQuestion extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookQuestionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    /**
     * 질문
     */
    @Lob
    private String question;

    /**
     * 추천
     */
    private int recommend = 0;


    public void addRecommend() {
        recommend++;
    }
}
