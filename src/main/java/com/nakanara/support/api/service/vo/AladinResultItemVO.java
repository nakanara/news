package com.nakanara.support.api.service.vo;

import lombok.Data;

@Data
public class AladinResultItemVO {

    private String title;
    private String link;
    private String author;
    private String pubDate;
    private String description;
    private String isbn;
    private String isbn13;
    private int itemId;
    private int priceSales;
    private int priceStandard;
    private String mallType;
    private String stockStatus;
    private int mileage;
    private String cover;
    private int categoryId;
    private String categoryName;
    private String publisher;
    private int salesPoint;
    private boolean adult;
    private boolean fixedPrice;
    private int customerReviewRank;

    private SeriesInfoVO seriesInfo;
    private SubInfoVO subInfo;
}
