package com.nakanara.news.repogitory;

import com.nakanara.news.dto.NewsEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface NewsEntityRepogitory extends CrudRepository<NewsEntity, Long> {
    List<NewsEntity> findAll(Sort direct);


}
