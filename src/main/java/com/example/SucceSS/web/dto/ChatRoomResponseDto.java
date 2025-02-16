package com.example.SucceSS.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatRoomResponseDto {
    private Long chatRoomId;
    private Long memberId;
    private String title;
    private String lastMessage;

    public ChatRoomResponseDto(Long chatRoomId, Long memberId, String title) {
        this.chatRoomId = chatRoomId;
        this.memberId = memberId;
        this.title = title;
    }
}
