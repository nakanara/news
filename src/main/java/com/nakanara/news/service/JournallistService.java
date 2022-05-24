package com.nakanara.news.service;

import com.nakanara.news.entity.Journalist;
import com.nakanara.news.repogitory.JournallistEntityRepogitory;
import com.sun.istack.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class JournallistService {

    private JournallistEntityRepogitory journallistEntityRepogitory;

    @Autowired
    public void setJournallistEntityRepogitory(JournallistEntityRepogitory journallistEntityRepogitory) {
        this.journallistEntityRepogitory = journallistEntityRepogitory;
    }

    public List<Journalist> getList() {
        return journallistEntityRepogitory.findAll();
    }

    @Transactional
    public Journalist post(@NotNull Journalist journalist) {

        journallistEntityRepogitory.save(journalist);

        return journalist;
    }

    public Journalist view(long id) {

        Journalist journalist = journallistEntityRepogitory.findById(id).orElse(null);


        return journalist;
    }
}
