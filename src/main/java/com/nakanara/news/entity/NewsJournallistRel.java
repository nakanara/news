package com.nakanara.news.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class NewsJournallistRel extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long njl_rel_id;

    @ManyToOne
    private News news;

    @ManyToOne
    private Journalist journalist;
}