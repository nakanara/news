package com.nakanara.news.repogitory;

import com.nakanara.news.dto.JournalistEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * package : com.nakanara.news.repogitory
 * class : JournallistEntityRepogitory.java
 * date: 2022-05-24 오전 12:26
 * user : jwpark
 * descr : 기자 프로필 관리
 *
 **/

public interface JournallistEntityRepogitory extends CrudRepository<JournalistEntity, Long> {

    @Override
    List<JournalistEntity> findAll();
}
