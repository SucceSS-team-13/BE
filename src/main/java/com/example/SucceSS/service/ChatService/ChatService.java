package com.example.SucceSS.service.ChatService;

import com.example.SucceSS.apiPayload.exception.ChatRoomNotFound;
import com.example.SucceSS.domain.Chat;
import com.example.SucceSS.domain.Member;
import com.example.SucceSS.repository.ChatRepository;
import com.example.SucceSS.repository.ChatRoomRepository;
import com.example.SucceSS.web.dto.ChatDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final ChatRoomRepository chatRoomRepository;

    @Transactional
    public ChatDto userSendChat(ChatDto chatDto, Member member) {
        Chat newChat = chatRepository.save(Chat.of(chatDto));
        chatRoomRepository.findByMemberId(member.getId())
                .orElseThrow(ChatRoomNotFound::new)
                .updatedAt(newChat.getSendDate());

        //ML 호출부분
        // 컴파일에러 방지 위해 유저 채팅 반환하도록 설정 : ML 응답결과로 생성된 Chat으로 변경해주세요!
        return ChatDto.from(newChat);

    }

}
