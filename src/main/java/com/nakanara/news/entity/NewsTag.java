package com.nakanara.news.entity;

import com.nakanara.core.entity.BaseEntity;
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

@Entity(name = "TB_NEWS_TAGS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        indexes = @Index(columnList = "tag, srcNewsId")
)
public class NewsTag extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long news_tag_id;

    @Column(nullable = false)
    private String tag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "srcNewsId")
    private News news;

}
