package com.nakanara.news.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * @package: com.nakanara.news.entity
 * @name: News.java
 * @date: 2022-05-26 오후 12:19
 * @author: nakan
 * @version: 1.0.0
 * @modifyed:
 * 
 * 뉴스 엔터티
 **/ 
// @Data
@Getter @Setter @ToString
@Builder
@Entity(name = "eso_news")
@NoArgsConstructor
@AllArgsConstructor
public class News extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long newsId;

    private String title;

    // 설명
    private String desc;

    // CLob
    @Lob
    private String content;

    @Column(length = 512)
    private String tag;

    @Column(columnDefinition = "int default 0")
    private int viewCount = 0;

    // 설명 - DB 미저장
    @Transient
    private String description;

    // 대표 이미지
    @Transient
    private String orgImage;

}
