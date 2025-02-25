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
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatRepository chatRepository;
    private final ConcurrentHashMap<Long, ScheduledFuture<?>> scheduledTasks = new ConcurrentHashMap<>();
    private final ThreadPoolTaskScheduler taskScheduler;


    @Transactional
    public ChatRoomResponseDto createChatRoom(Member member) {
        ChatRoom chatRoom = ChatRoom.builder()
                .memberId(member.getId())
                .title("New Chat")
                .build();

        chatRoom = chatRoomRepository.save(chatRoom);
        scheduleDeletion(chatRoom.getChatRoomId());
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

    @Transactional(readOnly = true)
    public Page<ChatRoomResponseDto> getChatRoomPages(Member member, Pageable pageable) {
        return chatRoomRepository.getPagesByMemberId(member.getId()
                , getChatRoomPageableWithSort(pageable));
    }

    private Pageable getChatRoomPageableWithSort(Pageable pageable) {
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()
                , Sort.by(Sort.Direction.DESC,"updatedAt"));
    }

    public void scheduleDeletion(Long chatRoomId) {
        ScheduledFuture<?> future = taskScheduler.schedule(
                () -> deleteChatRoom(chatRoomId),
                Instant.now().plusSeconds(5*60)
        );
        scheduledTasks.put(chatRoomId, future);
    }

    public void cancelScheduledDeletion(Long chatRoomId) {
        ScheduledFuture<?> future = scheduledTasks.get(chatRoomId);
        if (future != null && !future.isDone()) {
            future.cancel(false);
        }
        scheduledTasks.remove(chatRoomId);
    }



}
