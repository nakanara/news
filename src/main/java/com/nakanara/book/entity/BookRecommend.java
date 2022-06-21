package com.nakanara.book.entity;

import com.nakanara.core.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;

/**
 * package : com.nakanara.book.entity
 * class : BookRecommend.java
 * date: 2022-06-07 오후 3:04
 * user : jwpark
 * descr : 추천 도서
 *
 **/

@Data
@Entity(name = "TB_BOOK_RECOMMEND")
public class BookRecommend extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookListId;

    /**
     * 리스트 명
     */
    private String listName;

    @Column(length = 2000)
    private String descr;


}
