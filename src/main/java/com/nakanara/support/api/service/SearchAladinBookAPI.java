package com.nakanara.support.api.service;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.nakanara.support.api.service.vo.AladinResultItemVO;
import com.nakanara.support.api.service.vo.AladinResultVO;
import com.nakanara.util.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * package : com.nakanara.support.api.service
 * class : SearchAladinBookAPI.java
 * date: 2022-06-12 오후 11:09
 * user : jwpark
 * descr : 알라딘 책 검색
 *
 **/

@Slf4j
@Service
public class SearchAladinBookAPI {

    @Value("${aladinapi.baseurl}")
    private String BASE_URL;

    @Value("${aladinapi.ttb}")
    private String ttbKey;

    public AladinResultVO searchBook(String keyword, String start){

        Map<String, String> params = new HashMap<>();

        params.put("TTBKey", ttbKey);
        params.put("Query", keyword);
        params.put("start", start);
        params.put("MaxResults", "10");
        params.put("Output", "JS");
        params.put("Cover", "Big");
        params.put("Version", "20131101");

        String urlQuery = HttpUtil.addQuery(BASE_URL, params);

        String responseBody = HttpUtil.get(urlQuery, null);


        Gson gson = new Gson();
        AladinResultVO aladinResultVO =  gson.fromJson(responseBody, AladinResultVO.class);

        aladinResultVO.setItem(
                aladinResultVO.getItem().stream()
                .filter(aladinResultItemVO -> aladinResultItemVO.isIsbn13() ).collect(Collectors.toList())
        );


        return aladinResultVO;
    }
}
