package com.nakanara.news.entity;

import com.nakanara.core.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity(name = "TB_NEWS_JOURNAL_REL")
@NoArgsConstructor
@AllArgsConstructor
public class NewsJournallistRel extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long njlRelId;

    @ManyToOne(fetch = FetchType.LAZY)
    private News news;

    @OneToOne
    private Journallist journallist;
}
