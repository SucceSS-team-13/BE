package com.example.SucceSS.web.dto;

import com.example.SucceSS.domain.Chat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatDto {
    private Long chatRoomId;
    private Long memberId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sendDate;
    private String content;
    private String location;

    public void setOptionalLocation(String location) {
        this.location = location;
    }

    public static ChatDto toChatDto(Long chatRoomId, Long memberId, String content) {
        return ChatDto.builder()
                .chatRoomId(chatRoomId)
                .memberId(memberId)
                .content(content)
                .sendDate(LocalDateTime.now())
                .build();
    }

    public static ChatDto from(Chat chat) {
        return ChatDto.builder()
                .chatRoomId(chat.getChatRoomId())
                .memberId(chat.getMemberId())
                .sendDate(chat.getSendDate().atZone(ZoneId.of("Asia/Seoul")).toLocalDateTime())
                .content(chat.getContent())
                .build();
    }
}
