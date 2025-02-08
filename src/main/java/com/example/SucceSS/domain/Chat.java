package com.example.SucceSS.domain;

import com.example.SucceSS.web.dto.ChatDto;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "chatting")
public class Chat {
    @Id
    private String id;
    private Long chatRoomId;
    private Long memberId;
    private String content;
    private LocalDateTime sendDate;
    private String type;

    public static Chat of(ChatDto dto) {
        return Chat.builder()
                .chatRoomId(dto.getChatRoomId())
                .type(dto.getType())
                .content(dto.getContent())
                .memberId(dto.getMemberId())
                .sendDate(dto.getSendDate())
                .build();
    }

}
