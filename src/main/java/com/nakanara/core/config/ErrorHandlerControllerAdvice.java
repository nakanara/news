package com.nakanara.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ErrorHandlerControllerAdvice {


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String internalServerError(final Exception e) {
        log.error("internalServerError", e);
        return "redirect:/error/500";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String errNotFound(final Exception e) {
        log.error("errNotFound", e);
        return "redirect:/error/404";
    }



}
