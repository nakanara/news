package com.nakanara.core.config;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN"),
    SOCIAL("ROLE_SOCIAL"); // OAut

    private final String value;

}