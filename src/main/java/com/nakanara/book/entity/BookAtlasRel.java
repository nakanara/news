package com.nakanara.book.entity;

import com.nakanara.core.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "TB_BOOK_ATLAS_REL")
public class BookAtlasRel extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookAtlasRelId;

    @ManyToOne
    @JoinColumn(name = "src_book_atlas_id")
    private BookAtlas bookAtlas;
}
