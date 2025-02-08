package com.example.SucceSS.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ChatDto {
    private Long chatRoomId;
    private Long memberId;
    private LocalDateTime sendDate;
    private String content;
    private String type;

    public static ChatDto toChatDto(Long chatRoomId, Long memberId, String content, String type) {
        return ChatDto.builder()
                .chatRoomId(chatRoomId)
                .memberId(memberId)
                .content(content)
                .sendDate(LocalDateTime.now())
                .type(type)
                .build();
    }
}
