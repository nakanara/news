package com.nakanara.book.entity;

import com.nakanara.core.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "TB_BOOK_ATLAS")
public class BookAtlas extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookAtlasUid;


    @Column(name = "bat_title", length = 400)
    private String batTitle;

    @Lob
    private String batDescr;
    /**
     * 제한 달성자
     */
    private long limitCount;

    /**
     * 달성자 수
     */
    private long succCount;


    // private int likeCount = 0;

    /**
     * 책 권수
     */
    private int bookSize;

    @OneToMany
    @JoinColumn(name = "src_id")
    private List<FileAttach> fileAttaches;
}
