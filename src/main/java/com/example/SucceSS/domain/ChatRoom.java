package com.example.SucceSS.domain;

import com.example.SucceSS.domain.common.BaseEntity;
import com.example.SucceSS.web.dto.ChatRoomResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_room_id", nullable = false)
    private Long chatRoomId;

    @Column(name="member_id")
    private Long memberId;

    private String title;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public ChatRoomResponseDto toResponse() {
        return ChatRoomResponseDto.builder()
                .chatRoomId(this.chatRoomId)
                .memberId(this.memberId)
                .title(this.title)
                .build();
    }

    public void setUpdatedDate(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    public void updateChatRoomTitle(String title) { this.title = title; }

}
