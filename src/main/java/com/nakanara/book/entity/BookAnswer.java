package com.nakanara.book.entity;

import com.nakanara.core.entity.BaseEntity;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

/**
 * package : com.nakanara.book.entity
 * class : BookAnswer.java
 * date: 2022-06-07 오후 3:05
 * user : jwpark
 * descr : 답변
 *
 **/

@Data
@Entity(
        name = "BOOK_ANSWER"
)
public class BookAnswer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookAnswerId;

    @ManyToOne
    @JoinColumn(name = "src_question_id")
    private BookQuestion bookQuestion;

    @Lob
    private String answer;

    /**
     * 추천 수
     */
    private int recommend = 0;


}
