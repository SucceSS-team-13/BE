package com.example.SucceSS.web.dto;

import com.example.SucceSS.domain.Chat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ChatDto implements Serializable {
    private Long chatRoomId;
    private Long memberId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
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

    public static ChatDto from(Chat chat) {
        return ChatDto.builder()
                .chatRoomId(chat.getChatRoomId())
                .memberId(chat.getMemberId())
                .sendDate(chat.getSendDate())
                .content(chat.getContent())
                .type(chat.getType())
                .build();
    }
}
