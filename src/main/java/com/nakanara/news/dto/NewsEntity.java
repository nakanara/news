package com.nakanara.news.dto;


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
@Table(name = "TB_NEWS")
public class NewsEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    @Lob
    private String content;

    @Column(length = 512)
    private String tag;

    @OneToMany
    @JoinColumn(name = "news_id")
    private List<NewsTagEntity> tags;

    @Column(columnDefinition = "int default 0")
    private int viewCount = 0;

    // 설명
    @Transient
    private String description;

    // 대표 이미지
    @Transient
    private String orgImage;

    public void toConvertSNS(){
        setDescription(getTag().substring(1, 150));


    }
}
