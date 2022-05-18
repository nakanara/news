package com.nakanara.news.dto;


import com.nakanara.news.convert.NewsTagsListConverter;
import com.nakanara.news.convert.StringListConverter;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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



}
