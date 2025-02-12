package com.example.SucceSS.repository;

import com.example.SucceSS.domain.Chat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends MongoRepository<Chat, String> {
    void deleteByChatRoomId(Long chatRoomId);
    Page<Chat> findByChatRoomId(Long chatRoomId, Pageable pageable);
}
