package com.example.SucceSS.apiPayload.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoAuthorizationHeaderException extends RuntimeException{
    public NoAuthorizationHeaderException() {
        super("Authorization header가 존재하지 않습니다");
    }
}
