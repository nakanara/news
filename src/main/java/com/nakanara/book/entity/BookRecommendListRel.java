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

@Entity(name = "TB_BOOK_RECOMMEND_REL")
@Data
public class BookRecommendListRel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookListRelId;

    @ManyToOne
    @JoinColumn(name = "src_book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "src_book_list_id")
    private BookRecommend bookRecommend;
}
