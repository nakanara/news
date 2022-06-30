package com.nakanara.book.repository;

import com.nakanara.book.entity.BookAtlas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookAtlasRepo extends JpaRepository<BookAtlas, Long> {


    Page<BookAtlas> findAllByBatTitleLike(String keyword, Pageable pageRequest);
}
