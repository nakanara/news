package com.nakanara.book.repository;

import com.nakanara.book.entity.BookAtlas;
import com.nakanara.book.entity.BookAtlasRel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookAtlasRelRepo extends JpaRepository<BookAtlasRel, Long> {

    List<BookAtlasRel> getBookAtlasRelsByBookAtlas(BookAtlas BookAtlas);

    int deleteBookAtlasRelsByBookAtlas(BookAtlas bookAtlas);
}
