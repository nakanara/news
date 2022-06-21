package com.nakanara.book.entity;

import com.nakanara.core.entity.BaseEntity;
import com.nakanara.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "TB_MY_BOOK")
public class MyBook extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long myBookId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "src_user_id")
    private UserEntity userEntityId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "src_book_id")
    private Book bookId;

    private boolean bookLike;

    private String bookStatus;

    private LocalDateTime readStartDt;

    private LocalDateTime readEndDt;
    
    // 평점
    private int bookRating;
}
