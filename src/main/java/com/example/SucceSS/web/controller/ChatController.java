package com.example.SucceSS.web.controller;

import com.example.SucceSS.apiPayload.ApiResponse;
import com.example.SucceSS.service.ChatService.ChatRoomService;
import com.example.SucceSS.service.ChatService.ChatService;
import com.example.SucceSS.service.MemberService.MemberService;
import com.example.SucceSS.utils.GetCurrentUser;
import com.example.SucceSS.web.dto.ChatResponseDto;
import com.example.SucceSS.web.dto.ChatRequestDto;
import com.example.SucceSS.web.dto.ChatRoomResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;



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

    @DeleteMapping(value="/room/{chatRoomId}")
    @Operation(summary = "채팅방 삭제")
    public ResponseEntity<ApiResponse<Void>> deleteChatRoom(@PathVariable Long chatRoomId) {
        chatRoomService.deleteChatRoom( chatRoomId);
        return ResponseEntity.ok(ApiResponse.onSuccessWithMessage("채팅방 삭제 성공 : "+chatRoomId));
    }

    @GetMapping(value="/room/{chatRoomId}")
    @Operation(summary = "채팅방 내역 불러오기")
    public ResponseEntity<ApiResponse<Page<ChatResponseDto>>> getChatPages(@PathVariable Long chatRoomId, Pageable pageable) {
        return ResponseEntity.ok(
                ApiResponse.onSuccess(chatRoomService.getChatPages(chatRoomId, pageable)));
    }

    @GetMapping(value = "/rooms")
    @Operation(summary = "채팅방 목록 불러오기")
    public ResponseEntity<ApiResponse<Page<ChatRoomResponseDto>>> getChatRoomPages(Pageable pageable
            , @RequestParam(required = false, name = "keyword") String keyword) {
        return ResponseEntity.ok(
                ApiResponse.onSuccess(chatRoomService.getChatRoomPages(getCurrentUser.getCurrentUser(), pageable, keyword)));
    }


    @PostMapping()
    @Operation(summary = "유저 채팅 전송")
    public ResponseEntity<ApiResponse<ChatResponseDto>> sendMessage(@Valid @RequestBody ChatRequestDto chatDto) throws IOException {
        return ResponseEntity.ok(ApiResponse.onSuccess(
                chatService.userSendChat(chatDto, getCurrentUser.getCurrentUser())));
    }
}
