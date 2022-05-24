package com.nakanara.news.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany
    private List<NewsTag> tags;

    @Column(columnDefinition = "int default 0")
    private int viewCount = 0;

    @OneToMany
    private List<Journalist> journalist;

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
