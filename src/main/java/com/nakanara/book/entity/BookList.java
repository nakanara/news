package com.nakanara.book.entity;

import com.nakanara.core.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "BOOK_LIST")
public class BookList extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookListId;

    /**
     * 리스트 명
     */
    private String listName;

    @Column(length = 2000)
    private String descr;


}
