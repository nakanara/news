package com.nakanara.book.entity;

import com.nakanara.core.entity.BaseEntity;
import com.nakanara.user.entity.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class BookAnswerRecommend extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookRecommendUserId;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private BookAnswer bookAnswer;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
