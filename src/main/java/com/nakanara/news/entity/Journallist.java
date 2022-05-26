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
public class Journallist extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long journalistId;

    @Column(nullable = false)
    private String name;

    @Column
    private String nickname;

    @Column(nullable = false)
    private String email;

    @Column(length = 1024)
    private String desc;


}
