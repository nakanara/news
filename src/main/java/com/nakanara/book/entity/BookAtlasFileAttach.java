package com.nakanara.book.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "TB_BOOK_ATLAS_FILE")
public class BookAtlasFileAttach extends FileAttach{

    @ManyToOne
    @JoinColumn(name="book_atlas_id")
    private BookAtlas bookAtlas;
}
