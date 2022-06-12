package com.nakanara.support.api.service.vo;

import lombok.Data;

import java.util.List;

@Data
public class AladinResultVO {

    private String version;
    private String logo;
    private String title;
    private String link;
    private String pubDate;
    private int totalResults;
    private int startIndex;
    private int itemsPerPage;
    private String query;
    private String searchCategoryId;
    private String searchCategoryName;

    private List<AladinResultItemVO> item;

}
