package com.nakanara.news.entity;


import com.nakanara.core.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@Entity(name = "TB_NEWS_COMMENT")
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long commId;

    @ManyToOne(fetch = FetchType.LAZY)
    private News news;

    @Column(length = 2000)
    private String comment;
}
