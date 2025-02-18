package com.example.SucceSS.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatRoomResponseDto {
    private Long chatRoomId;
    private Long memberId;
    private String title;
    private String lastMessageDate;

    public ChatRoomResponseDto(Long chatRoomId, Long memberId, String title, LocalDateTime updatedAt) {
        this.chatRoomId = chatRoomId;
        this.memberId = memberId;
        this.title = title;
        this.lastMessageDate = updatedAt.atOffset(ZoneOffset.UTC)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
    }
}
