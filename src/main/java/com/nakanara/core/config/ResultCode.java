package com.nakanara.core.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResultCode {

    SUCCESS("SUCCESS"),
    FAIL("FAIL"),
    GUEST("GUEST");

    private final String value;
}
