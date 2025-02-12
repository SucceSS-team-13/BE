package com.example.SucceSS.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ChatRoomResponseDto {
    private Long chatRoomId;
    private Long memberId;
    private String title;
}
