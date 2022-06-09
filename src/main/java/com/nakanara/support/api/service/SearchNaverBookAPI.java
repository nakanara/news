package com.nakanara.support.api.service;


import com.nakanara.util.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class SearchNaverBookAPI {

    @Value("${naverapi.baseurl}")
    private String BASE_URL;

    @Value("${naverapi.clientId}")
    private String clientId;

    @Value("${naverapi.clientSecret}")
    private String clientSecret;


    public void searchBook(String keyword){

        String text = "";
        try {
            text = URLEncoder.encode(keyword, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }


        String apiURL = BASE_URL + "/search/blog?query=" + text;    // json 결과


        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = HttpUtil.get(apiURL,requestHeaders);


        System.out.println(responseBody);

    }

}
