package com.example.SucceSS.domain;

import com.example.SucceSS.domain.common.BaseEntity;
import com.example.SucceSS.web.dto.ChatRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    @Column(name="text", columnDefinition = "LONGTEXT")
    private String text;
    private LocalDateTime sendDate;
    private String location;
    private String sender;

    public static Chat of(ChatRequestDto dto, Long memberId) {
        return Chat.builder()
                .chatRoomId(dto.getChatRoomId())
                .text(dto.getText())
                .memberId(memberId)
                .sendDate(LocalDateTime.now())
                .sender("user")
                .build();
    }

    public static Chat of(Long chatRoomId, Long memberId, String text, String sender) {
        return Chat.builder()
                .chatRoomId(chatRoomId)
                .text(text)
                .memberId(memberId)
                .sendDate(LocalDateTime.now())
                .sender(sender)
                .build();
    }
}
