package com.nakanara.book.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * package : com.nakanara.book.entity
 * class : BookRecommendListRel.java
 * date: 2022-06-07 오후 3:04
 * user : jwpark
 * descr : 추천 도서 목록
 *
 **/

@Entity(name = "BOOK_LIST_REL")
@Data
public class BookRecommendListRel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookListRelId;

    @ManyToOne
    private Book book;

    @ManyToOne
    private BookRecommend bookRecommend;
}
