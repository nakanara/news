package com.nakanara.news.service;

import com.nakanara.news.dto.TbNews;
import com.nakanara.news.repogitory.TbNewsRepogitory;
import com.nakanara.util.TimeUtil;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    @Autowired
    TbNewsRepogitory tbNewsRepogitory;
    public TbNews post(@NotNull  TbNews tbNews) {

        tbNewsRepogitory.save(tbNews);
        return tbNews;
    }

    public List<TbNews> getList() {
        return tbNewsRepogitory.findAll();
    }

    public boolean delete(long id) {

        tbNewsRepogitory.delete(
            TbNews.builder().id(id).build()
        );

        return true;
    }
}
