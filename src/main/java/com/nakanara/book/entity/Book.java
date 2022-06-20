package com.nakanara.book.entity;

import com.nakanara.core.entity.BaseEntity;
import com.nakanara.support.api.service.vo.AladinResultItemVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor @NoArgsConstructor
@Entity(name = "BOOK")
@Table(
        indexes = {
                @Index(columnList = "isbn")
        }
)
@DynamicUpdate
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

    @Column(nullable = false, unique = true)
    private String isbn13;

    private String categoryName;


    /**
     * 좋아요 수
     */
    private long likeCount;

   /**
     * 설명
     */
    @Lob
    private String description;

    public static Book convertAladinItem(AladinResultItemVO aladinResultItemVO) {

        return Book.builder()
                .title(aladinResultItemVO.getTitle())
                .link(aladinResultItemVO.getLink())
                .pubdate(aladinResultItemVO.getPubDate())
                .publisher(aladinResultItemVO.getPublisher())
                .author(aladinResultItemVO.getAuthor())
                .description(aladinResultItemVO.getDescription())
                .image(aladinResultItemVO.getCover())
                .isbn(aladinResultItemVO.getIsbn())
                .isbn13(aladinResultItemVO.getIsbn13())
                .categoryName(aladinResultItemVO.getCategoryName())
                .build();
    }
}
