package com.nakanara.support.api.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SearchAladinBookAPITest {

    @Autowired
    private SearchAladinBookAPI searchAladinBookAPI;

    @Test
    void searchAPI(){
        searchAladinBookAPI.searchBook("미움받을 용기");
    }
}