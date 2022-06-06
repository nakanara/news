package com.nakanara.news.entity;


import com.nakanara.core.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class News extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long newsId;

    private String title;

    @Lob
    private String content;

    @Column(length = 512)
    private String tag;


    @Column(columnDefinition = "int default 0")
    private int viewCount = 0;

    @Transient
    private List<Long> journallist;

    // 설명 - DB 미저장
    @Transient
    private String description;

    // 대표 이미지
    @Transient
    private String orgImage;

    public void toConvertSNS(){
        setDescription(getTag().substring(1, 150));
    }
}
