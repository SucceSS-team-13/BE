package com.example.SucceSS.apiPayload.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MemberNotFound extends RuntimeException{
    public MemberNotFound() {
        super("사용자를 찾을 수 없습니다.");
    }
}
