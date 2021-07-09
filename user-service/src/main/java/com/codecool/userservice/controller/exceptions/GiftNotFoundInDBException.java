package com.codecool.userservice.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GiftNotFoundInDBException extends RuntimeException {

    public GiftNotFoundInDBException() {
        super();
    }
}
