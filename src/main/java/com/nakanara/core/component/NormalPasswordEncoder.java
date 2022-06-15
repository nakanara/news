package com.nakanara.core.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NormalPasswordEncoder implements PasswordEncoder {

    @Override
    public boolean upgradeEncoding(String encodedPassword) {

        log.info("{}", encodedPassword);
        return true;
    }

    @Override
    public String encode(CharSequence rawPassword) {
        log.info("{}", rawPassword);
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {

        log.info("{}, {}", rawPassword, encodedPassword);
        return (rawPassword.equals(encodedPassword));
    }
}
