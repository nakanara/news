package com.nakanara.book.entity;

import com.nakanara.core.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "BOOK")
@Table(
        indexes = {
                @Index(columnList = "isbn")
        }
)
public class Book extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookId;

    /**
     * 제목
     */
    @Column(length = 2000)
    private String title;

    @Column(length = 1000)
    private String link;

    /**
     * 썸네일
     */
    @Column(length = 2000)
    private String image;

    /**
     * 저자
     */
    private String author;

    /**
     * 가격
     */
    private long price;

    /**
     * 할인 가격
     */
    private long discount;

    /**
     * 출발사
     */
    private String publisher;

    /**
     * 발행일
     */
    private String pubdate;

    /**
     * ISBN
     */
    private String isbn;

    /**
     * 설명
     */
    @Lob
    private String description;
}
