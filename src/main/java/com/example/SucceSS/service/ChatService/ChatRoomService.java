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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Transactional
    // MongoDB 트랜잭션 설정 필요
    public void deleteChatRoom(Long chatRoomId) {
        chatRepository.deleteByChatRoomId(chatRoomId);
        chatRoomRepository.deleteById(chatRoomId);
    }

    public Page<ChatDto> getChatPages(Long chatRoomId, Pageable pageable) {
        return chatRepository.findByChatRoomId(chatRoomId, pageable)
                .map(ChatDto::from);
    }

    private void sendFirstMessage(ChatRoom chatRoom) {
        ChatDto dto = ChatDto.toChatDto(chatRoom.getChatRoomId(),chatRoom.getMemberId(), "New Chat", "CHAT");
        producer.sendMessageToUser(dto);

        chatRepository.save(Chat.of(dto));
    }

}
