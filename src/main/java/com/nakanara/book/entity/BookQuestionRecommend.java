package com.nakanara.book.entity;

import com.nakanara.user.entity.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "BOOK_QUESTION_RECOMMEND")
public class BookQuestionRecommend {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookRecommendUserId;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private BookQuestion bookQuestion;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
