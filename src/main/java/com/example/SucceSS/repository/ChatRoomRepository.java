package com.example.SucceSS.repository;

import com.example.SucceSS.domain.ChatRoom;
import com.example.SucceSS.web.dto.ChatRoomResponseDto;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long> {
    @Query("SELECT new com.example.SucceSS.web.dto.ChatRoomResponseDto(c.chatRoomId, c.memberId, c.title) " +
            "FROM ChatRoom c " +
            "WHERE c.memberId = :memberId")
    Page<ChatRoomResponseDto> findByMemberId(@Param("memberId") Long memberId, Pageable pageable);
}
