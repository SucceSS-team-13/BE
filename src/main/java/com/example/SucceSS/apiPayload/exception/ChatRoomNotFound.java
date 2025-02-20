package com.example.SucceSS.apiPayload.exception;

public class ChatRoomNotFound extends RuntimeException{
    public ChatRoomNotFound() {
        super("채팅방을 찾을 수 없습니다.");
    }
}
