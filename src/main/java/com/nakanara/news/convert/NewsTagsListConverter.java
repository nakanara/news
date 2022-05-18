package com.nakanara.news.convert;

import com.nakanara.news.dto.NewsTagEntity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class NewsTagsListConverter implements AttributeConverter<List<NewsTagEntity>, String> {
    private static final String SPLIT_CHAR = ",";

    @Override
    public String convertToDatabaseColumn(List<NewsTagEntity> newsTagEntities) {

        StringBuffer buf = new StringBuffer();

        for(NewsTagEntity t : newsTagEntities) {
            if(buf.length() > 0) {
                buf.append(SPLIT_CHAR);
            }
            buf.append(t.getTag());
        }

        return buf.toString();
    }

    @Override
    public List<NewsTagEntity> convertToEntityAttribute(String string) {

        List<NewsTagEntity> newsTagEntits = new ArrayList<>();

        if(string == null) return newsTagEntits;

        String [] token = string.split(SPLIT_CHAR);

        for(String t : token) {
            NewsTagEntity newsTagEntity = new NewsTagEntity();
            newsTagEntity.setTag(t);

            newsTagEntits.add(newsTagEntity);
        }


        return newsTagEntits;
    }
}