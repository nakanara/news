package com.nakanara.news.repogitory;

import com.nakanara.news.dto.TbNews;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TbNewsRepogitory extends CrudRepository<TbNews, Long> {
    List<TbNews> findAll();
}
