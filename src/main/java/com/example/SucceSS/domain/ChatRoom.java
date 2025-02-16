package com.example.SucceSS.domain;

import com.example.SucceSS.domain.common.BaseEntity;
import com.example.SucceSS.web.dto.ChatRoomResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_room_id", nullable = false)
    private Long chatRoomId;

    @Column(name="member_id")
    private Long memberId;

    private String title;

    public ChatRoomResponseDto toResponse() {
        return ChatRoomResponseDto.builder()
                .chatRoomId(this.chatRoomId)
                .memberId(this.memberId)
                .title(this.title)
                .build();
    }

}
