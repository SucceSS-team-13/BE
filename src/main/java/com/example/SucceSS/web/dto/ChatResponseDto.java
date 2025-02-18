package com.example.SucceSS.web.dto;

import com.example.SucceSS.domain.Chat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatResponseDto {
    private Long chatRoomId;
    private Long memberId;
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sendDate;
    private String text;
    private String sender;
    private String location;

    public void setOptionalLocation(String location) {
        this.location = location;
    }
    public void selectSender(String sender) { this.sender = sender; }

    public static ChatResponseDto toChatDto(Long chatRoomId, Long id, Long memberId, String content, String sender) {
        return ChatResponseDto.builder()
                .chatRoomId(chatRoomId)
                .id(id)
                .memberId(memberId)
                .text(content)
                .sendDate(LocalDateTime.now())
                .sender(sender)
                .build();
    }

    public static ChatResponseDto fromWithoutLocation(Chat chat) {
        return ChatResponseDto.builder()
                .chatRoomId(chat.getChatRoomId())
                .memberId(chat.getMemberId())
                .id(chat.getChatId())
                .sendDate(chat.getSendDate())
                .text(chat.getText())
                .sender(chat.getSender())
                .build();
    }
}
