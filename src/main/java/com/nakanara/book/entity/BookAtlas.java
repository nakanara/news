package com.nakanara.book.entity;

import com.nakanara.core.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "TB_BOOK_ATLAS")
public class BookAtlas extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookAtlasUid;


    @Column(name = "bat_title", length = 400)
    private String batTitle;

    /**
     * 제한 달성자
     */
    private int limitCount;

    /**
     * 달성자 수
     */
    private long succCount;

    /**
     * 책 권수
     */
    private int bookSize;

    @OneToMany
    private List<BookAtlasRel> bookAtlasRels;
}
