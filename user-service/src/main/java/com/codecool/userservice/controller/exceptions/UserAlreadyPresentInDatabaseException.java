package com.codecool.userservice.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserAlreadyPresentInDatabaseException extends RuntimeException {
    public UserAlreadyPresentInDatabaseException() {
        super();
    }
}
