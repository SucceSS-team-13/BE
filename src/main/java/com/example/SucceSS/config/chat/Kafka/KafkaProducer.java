package com.example.SucceSS.config.chat.Kafka;

import com.example.SucceSS.service.ChatService.ChatRoomService;
import com.example.SucceSS.web.dto.ChatDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {
    private final KafkaTemplate<String, ChatDto> kafkaTemplate;
    private final String USER_TOPIC = "user-request";
    private final String AI_TOPIC = "ai-response";

    public void sendMessageToUser(ChatDto dto) {
        CompletableFuture<SendResult<String, ChatDto>> completableFuture =
                kafkaTemplate.send(AI_TOPIC, dto).toCompletableFuture();

        completableFuture
                .thenAccept(result -> log.info("Successfully sent message: {}", dto.getContent()))
                .exceptionally(ex -> {
                    log.error("Failed to send message: {}", dto.getContent(), ex);
                    return null;
                });
        log.info("Produce message: {}", dto.getContent());
    }

    public void sendMessageToAI() {
        // jwt 검증 로직 필요
    }


}
