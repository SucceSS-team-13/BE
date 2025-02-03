package com.example.SucceSS.apiPayload.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoBearerException extends RuntimeException{
    public NoBearerException() {
        super("Bearer 토큰이 존재하지 않습니다.");
    }
}
