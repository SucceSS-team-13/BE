package com.example.SucceSS.service.ChatService;

import com.example.SucceSS.apiPayload.exception.ChatRoomNotFound;
import com.example.SucceSS.domain.Chat;
import com.example.SucceSS.domain.ChatRoom;
import com.example.SucceSS.domain.Member;
import com.example.SucceSS.repository.ChatRepository;
import com.example.SucceSS.repository.ChatRoomRepository;
import com.example.SucceSS.web.dto.ChatResponseDto;
import com.example.SucceSS.web.dto.ChatRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final ChatRoomRepository chatRoomRepository;
    private static final String USER = "user";
    private static final String LUMI = "lumi";

    @Transactional
    public ChatResponseDto userSendChat(ChatRequestDto chatDto, Member member) {
        updateChatRoomInfos(chatRoomRepository.findByChatRoomId(chatDto.getChatRoomId())
                .orElseThrow(ChatRoomNotFound::new), chatDto);

        Chat newChat = chatRepository.save(Chat.of(chatDto,member.getId()));


        //ML 호출부분
        // 컴파일에러 방지 위해 유저 채팅 반환하도록 설정 : ML 응답결과로 생성된 Chat으로 변경해주세요!
        return ChatResponseDto.fromWithoutLocation(newChat);
    }

    public void updateChatRoomInfos(ChatRoom chatRoom, ChatRequestDto chatDto) {
        // 첫 채팅 발생하면 채팅방 제목 변경
        if(chatRepository.findByChatRoomId(chatRoom.getChatRoomId()).isEmpty()) {
            chatRoom.updateChatRoomTitle(chatDto.getText());
        }
        chatRoom.setUpdatedDate(LocalDateTime.now());
    }

}
