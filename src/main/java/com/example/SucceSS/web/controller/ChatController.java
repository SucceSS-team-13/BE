package com.example.SucceSS.web.controller;

import com.example.SucceSS.apiPayload.ApiResponse;
import com.example.SucceSS.service.ChatService.ChatRoomService;
import com.example.SucceSS.service.ChatService.ChatService;
import com.example.SucceSS.service.MemberService.MemberService;
import com.example.SucceSS.utils.GetCurrentUser;
import com.example.SucceSS.web.dto.ChatDto;
import com.example.SucceSS.web.dto.ChatRoomResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/chat")
public class ChatController {
    private final MemberService memberService;
    private final GetCurrentUser getCurrentUser;
    private final ChatRoomService chatRoomService;

    private final ChatService chatService;


    @PostMapping(value = "/room")
    @Operation(summary = "채팅방 생성")
    public ResponseEntity<ApiResponse<ChatRoomResponseDto>> createChatRoom() {
        return ResponseEntity.ok(ApiResponse.onSuccess(chatRoomService.createChatRoom(getCurrentUser.getCurrentUser())));
    }

    // pub/chat/message 경로로 메세지 전송 : setApplicationDestinationPrefixes
    @MessageMapping("/chat/message")
    @Operation(summary = "웹소켓 메세지 전송")
    public void sendSocketMessage(@Valid @RequestBody ChatDto chatDto){
        //return ResponseEntity.ok(ApiResponse.onSuccess(chatRoomService.userSendChat(getCurrentUser.getCurrentUser())));
    }
}
