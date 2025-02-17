package com.example.SucceSS.domain;

import com.example.SucceSS.domain.common.BaseEntity;
import com.example.SucceSS.web.dto.ChatDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Chat extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id", nullable = false)
    private Long chatId;
    @Column(name="chat_room_id", nullable = false)
    private Long chatRoomId;
    @Column(name="member_id", nullable = false)
    private Long memberId;
    private String content;
    private LocalDateTime sendDate;
    private String location;

    public static Chat of(ChatDto dto) {
        return Chat.builder()
                .chatRoomId(dto.getChatRoomId())
                .content(dto.getContent())
                .memberId(dto.getMemberId())
                .sendDate(LocalDateTime.now(ZoneId.of("Asia/Seoul")))
                .build();
    }

}
