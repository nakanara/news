package com.nakanara.book.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @package: com.nakanara.book.entity
 * @name: MyBookStatus.java
 * @date: 2022-06-20 오후 12:40
 * @author: nakan
 * @version: 1.0.0
 * @modifyed: 책을 일고 있는 상태
 **/
public class MyBookStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long myBookStatusUid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "src_mybook_id")
    private MyBook myBook;

    private LocalDateTime startDt;

    private LocalDateTime endDt;

    private long page = 0;

    // 현재 여부.
    private int curFlag = 0;



}
