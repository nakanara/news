package com.nakanara.news.service;

import com.nakanara.news.entity.Journallist;
import com.nakanara.news.repogitory.JournallistRepository;
import com.sun.istack.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class JournallistService {

    private JournallistRepository journallistRepository;

    @Autowired
    public void setJournallistRepository(JournallistRepository journallistRepository) {
        this.journallistRepository = journallistRepository;
    }

    public List<Journallist> getList() {
        return journallistRepository.findAll();
    }

    @Transactional
    public Journallist post(@NotNull Journallist journalist) {

        journallistRepository.save(journalist);

        return journalist;
    }

    public Journallist view(long id) {

        Journallist journalist = journallistRepository.findById(id).orElse(null);


        return journalist;
    }
}
