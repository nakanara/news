package com.nakanara.book.service;

import com.nakanara.book.entity.BookAtlas;
import com.nakanara.book.entity.BookAtlasRel;
import com.nakanara.book.repository.BookAtlasRelRepo;
import com.nakanara.book.repository.BookAtlasRepo;
import com.nakanara.core.vo.ResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookAtlasService {

    private final BookAtlasRepo bookAtlasRepo;

    private final BookAtlasRelRepo bookAtlasRelRepo;

    /**
     * 추천 도서
     * @param bookAtlasId
     * @return
     */
    public BookAtlas getBookAtlas(long bookAtlasId) {
        return bookAtlasRepo.getById(bookAtlasId);
    }

    @Transactional
    public BookAtlas saveBookAtlas(BookAtlas bookAtlas, List<BookAtlasRel> bookAtlasRels) {

        bookAtlasRepo.save(bookAtlas);

        bookAtlasRels.stream().forEach( item -> {
            item.setBookAtlas(bookAtlas);
            bookAtlasRelRepo.save(item);
        });

        return bookAtlas;
    }

    public ResultVO getBookAtlasList(int page, int size) {

        Page<BookAtlas> pagedResult = bookAtlasRepo.findAll(PageRequest.of(page-1, size, Sort.by("bookAtlasUid").descending()));

        log.info("{}", pagedResult.toString());

        if(pagedResult.hasContent()) {
            return ResultVO.builder().data(pagedResult.getContent())
                    .totalPage(pagedResult.getTotalPages())
                    .curPage(page)
                    .build();

        } else {
            return ResultVO.builder().data(new ArrayList<>())
                    .totalPage(0)
                    .curPage(0)
                    .build();
        }
    }
}
