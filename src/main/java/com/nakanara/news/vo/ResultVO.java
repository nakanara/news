package com.nakanara.news.vo;

import lombok.Builder;
import lombok.Data;

/**
 * 화면 전송 객체
 */
@Data
@Builder
public class ResultVO {

    private String code;

    private Object data;

    private String message;

    private int totalPage;
    private int curPage;
}
