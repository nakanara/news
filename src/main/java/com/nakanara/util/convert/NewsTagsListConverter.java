package com.nakanara.util.convert;

import com.nakanara.news.entity.NewsTag;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.List;

@Converter
public class NewsTagsListConverter implements AttributeConverter<List<NewsTag>, String> {
    private static final String SPLIT_CHAR = ",";

    @Override
    public String convertToDatabaseColumn(List<NewsTag> newsTagEntities) {

        StringBuffer buf = new StringBuffer();

        for(NewsTag t : newsTagEntities) {
            if(buf.length() > 0) {
                buf.append(SPLIT_CHAR);
            }
            buf.append(t.getTag());
        }

        return buf.toString();
    }

    @Override
    public List<NewsTag> convertToEntityAttribute(String string) {

        List<NewsTag> newsTagEntits = new ArrayList<>();

        if(string == null) return newsTagEntits;

        String [] token = string.split(SPLIT_CHAR);

        for(String t : token) {
            NewsTag newsTag = new NewsTag();
            newsTag.setTag(t);

            newsTagEntits.add(newsTag);
        }


        return newsTagEntits;
    }
}