package com.example.SucceSS.service.ChatService;

import com.example.SucceSS.config.chat.Kafka.KafkaProducer;
import com.example.SucceSS.domain.Chat;
import com.example.SucceSS.domain.ChatRoom;
import com.example.SucceSS.domain.Member;
import com.example.SucceSS.repository.ChatRepository;
import com.example.SucceSS.repository.ChatRoomRepository;
import com.example.SucceSS.repository.MemberRepository;
import com.example.SucceSS.web.dto.ChatDto;
import com.example.SucceSS.web.dto.ChatRoomResponseDto;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final MemberRepository memberRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRepository chatRepository;
    private final KafkaProducer producer;

    @Transactional
    public ChatRoomResponseDto createChatRoom(Member member) {
        ChatRoom chatRoom = ChatRoom.builder()
                .memberId(member.getId())
                .title("New Chat")
                .build();

        chatRoomRepository.save(chatRoom);

        sendFirstMessage(chatRoom);
        return chatRoom.toResponse();
    }

    private void sendFirstMessage(ChatRoom chatRoom) {
        ChatDto dto = ChatDto.toChatDto(chatRoom.getChatRoomId(),chatRoom.getMemberId(), "New Chat", "CHAT");
        producer.sendMessageToUser(dto);

        chatRepository.save(Chat.of(dto));
    }

}
