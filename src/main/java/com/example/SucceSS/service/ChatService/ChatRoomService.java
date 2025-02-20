package com.example.SucceSS.service.ChatService;

import com.example.SucceSS.domain.ChatRoom;
import com.example.SucceSS.domain.Member;
import com.example.SucceSS.repository.ChatRepository;
import com.example.SucceSS.repository.ChatRoomRepository;
import com.example.SucceSS.web.dto.ChatResponseDto;
import com.example.SucceSS.web.dto.ChatRoomResponseDto;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatRepository chatRepository;

    @Transactional
    public ChatRoomResponseDto createChatRoom(Member member) {
        ChatRoom chatRoom = ChatRoom.builder()
                .memberId(member.getId())
                .title("New Chat")
                .build();

        chatRoomRepository.save(chatRoom);

        //sendFirstMessage(chatRoom);
        return chatRoom.toResponse();
    }

    @Transactional
    public void deleteChatRoom(Long chatRoomId) {
        chatRepository.deleteByChatRoomId(chatRoomId);
        chatRoomRepository.deleteById(chatRoomId);
    }

    @Transactional(readOnly = true)
    public Page<ChatResponseDto> getChatPages(Long chatRoomId, Pageable pageable) {
        return chatRepository.findByChatRoomId(chatRoomId, pageable)
                .map(ChatResponseDto::fromWithoutLocation);
    }

    /*
    private void sendFirstMessage(ChatRoom chatRoom) {
        ChatDto dto = ChatDto.toChatDto(chatRoom.getChatRoomId(),chatRoom.getMemberId(), "New Chat", "CHAT");
        producer.sendMessageToUser(dto);

        chatRepository.save(Chat.of(dto));
    }
     */

    @Transactional(readOnly = true)
    public Page<ChatRoomResponseDto> getChatRoomPages(Member member, Pageable pageable) {
        return chatRoomRepository.getPagesByMemberId(member.getId()
                , getChatRoomPageableWithSort(pageable));
    }

    private Pageable getChatRoomPageableWithSort(Pageable pageable) {
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()
                , Sort.by(Sort.Direction.DESC,"updatedAt"));
    }

}
