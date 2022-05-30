package com.nakanara.news.entity;


import lombok.*;

import javax.persistence.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long commId;

    // 1 - N 관계
    @ManyToOne
    private News news;

    @Column(length = 2000)
    private String comment;
}
