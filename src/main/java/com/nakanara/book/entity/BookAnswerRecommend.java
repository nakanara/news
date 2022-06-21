package com.nakanara.book.entity;

import com.nakanara.core.entity.BaseEntity;
import com.nakanara.user.entity.UserEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(
        name = "TB_BOOK_ANSWER_RECOMMEND"
)
public class BookAnswerRecommend extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookRecommendUserId;

    @ManyToOne
    @JoinColumn(name = "src_answer_id")
    private BookAnswer bookAnswer;


    @ManyToOne
    @JoinColumn(name = "src_user_id")
    private UserEntity userEntity;
}
