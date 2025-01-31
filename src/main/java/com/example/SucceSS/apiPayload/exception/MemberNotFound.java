package com.example.SucceSS.apiPayload.exception;

public class MemberNotFound extends RuntimeException{
    public MemberNotFound() {
        super("사용자를 찾을 수 없습니다.");
    }
}
