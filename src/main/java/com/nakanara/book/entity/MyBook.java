package com.nakanara.book.entity;

import com.nakanara.core.entity.BaseEntity;
import com.nakanara.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "TB_MY_BOOK")
public class MyBook extends BaseEntity {

    @Id
    @Column(name = "mybook_id")
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

    @OneToMany(mappedBy = "myBook")
    private List<MyBookFileAttach> files = new ArrayList<>();
}
