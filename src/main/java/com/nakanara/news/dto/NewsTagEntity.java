package com.nakanara.news.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * package : com.nakanara.news.dto
 * class : NewsTag.java
 * date: 2022-05-19 오전 12:29
 * user : jwpark
 * descr : 뉴스 태그
 *
 **/

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_NEWSTAG")
public class NewsTagEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String tag;

    @ManyToOne
    @JoinColumn(name = "news_id", insertable = false, updatable = false)
    private NewsEntity newsEntity;

}
