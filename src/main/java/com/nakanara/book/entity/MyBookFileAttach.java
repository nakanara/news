package com.nakanara.book.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "TB_MY_BOOK_FILE")
public class MyBookFileAttach extends FileAttach{

    @ManyToOne
    @JoinColumn(name="mybook_id")
    private MyBook myBook;
}
