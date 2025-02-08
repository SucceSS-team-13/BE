package com.example.SucceSS.web.controller;

import com.example.SucceSS.apiPayload.ApiResponse;
import com.example.SucceSS.service.ChatService.ChatRoomService;
import com.example.SucceSS.service.MemberService.MemberService;
import com.example.SucceSS.utils.GetCurrentUser;
import com.example.SucceSS.web.dto.ChatRoomResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/chat")
public class ChatController {
    private final MemberService memberService;
    private final GetCurrentUser getCurrentUser;
    private final ChatRoomService chatRoomService;


    @PostMapping(value = "/room")
    public ResponseEntity<ApiResponse<ChatRoomResponseDto>> createChatRoom() {
        return ResponseEntity.ok(ApiResponse.onSuccess(chatRoomService.createChatRoom(getCurrentUser.getCurrentUser())));
    }
}
