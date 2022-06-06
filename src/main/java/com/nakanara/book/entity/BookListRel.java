package com.nakanara.book.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "BOOK_LIST_REL")
@Data
public class BookListRel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookListRelId;

    @ManyToOne
    private Book book;

    @ManyToOne
    private BookList bookList;
}
