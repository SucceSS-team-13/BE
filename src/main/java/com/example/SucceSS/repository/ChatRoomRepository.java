package com.example.SucceSS.repository;

import com.example.SucceSS.domain.ChatRoom;
import com.example.SucceSS.web.dto.ChatRoomResponseDto;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long> {
    @Query("SELECT new com.example.SucceSS.web.dto.ChatRoomResponseDto(c.chatRoomId, c.memberId, c.title, c.updatedAt) " +
            "FROM ChatRoom c " +
            "WHERE c.memberId = :memberId")
    Page<ChatRoomResponseDto> getPagesByMemberId(@Param("memberId") Long memberId, Pageable pageable);

    @Query("SELECT new com.example.SucceSS.web.dto.ChatRoomResponseDto(c.chatRoomId, c.memberId, c.title, c.updatedAt) " +
            "FROM ChatRoom c " +
            "WHERE c.memberId = :memberId " +
            "AND c.title LIKE CONCAT('%', :keyword, '%')")
    Page<ChatRoomResponseDto> getPagesByMemberIdAndSort(@Param("memberId") Long memberId, Pageable pageable,@Param("keyword") String keyword);

    Optional<ChatRoom> findByChatRoomId(Long chatRoomId);
}
