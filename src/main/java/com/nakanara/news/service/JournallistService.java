package com.nakanara.news.service;

import com.nakanara.news.dto.JournalistEntity;
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

    public List<JournalistEntity> getList() {
        return journallistEntityRepogitory.findAll();
    }

    @Transactional
    public JournalistEntity post(@NotNull JournalistEntity journalistEntity) {

        journallistEntityRepogitory.save(journalistEntity);

        return journalistEntity;
    }

    public JournalistEntity view(long id) {

        JournalistEntity journalistEntity = journallistEntityRepogitory.findById(id).orElse(null);


        return journalistEntity;
    }
}
