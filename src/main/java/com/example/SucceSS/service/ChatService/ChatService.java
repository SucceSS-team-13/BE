package com.example.SucceSS.service.ChatService;

import com.example.SucceSS.config.chat.Kafka.KafkaProducer;
import com.example.SucceSS.domain.Chat;
import com.example.SucceSS.domain.Member;
import com.example.SucceSS.repository.ChatRepository;
import com.example.SucceSS.web.dto.ChatDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final KafkaProducer producer;
    private final ChatRepository chatRepository;

    @Transactional
    public void userSendChat(ChatDto chatDto, Member member) {
        chatRepository.save(Chat.of(chatDto));
        // 굳이 발행하지 않고 모델로 send 하는 코드 넣으면 될 듯
        producer.sendMessageToAI(chatDto);
        // webClient.send(dto)로 받은 결과물을 -> producer.sendMessageToUser


    }

}
