package com.nakanara.news.service;

import com.nakanara.news.entity.Journallist;
import com.nakanara.news.repogitory.JournallistRepogitory;
import com.sun.istack.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class JournallistService {

    private JournallistRepogitory journallistRepogitory;

    @Autowired
    public void setJournallistRepogitory(JournallistRepogitory journallistRepogitory) {
        this.journallistRepogitory = journallistRepogitory;
    }

    public List<Journallist> getList() {
        return journallistRepogitory.findAll();
    }

    @Transactional
    public Journallist post(@NotNull Journallist journalist) {

        journallistRepogitory.save(journalist);

        return journalist;
    }

    public Journallist view(long id) {

        Journallist journalist = journallistRepogitory.findById(id).orElse(null);


        return journalist;
    }
}
