package com.nakanara.book.entity.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BookRead {

    // 읽기 전
    READY("READY"),
    // 읽는 중
    READ("READ"),
    // 전체 읽음
    FINISH("FINISH"),
    // 중단
    STOP("STOP");

    private final String value;

}
