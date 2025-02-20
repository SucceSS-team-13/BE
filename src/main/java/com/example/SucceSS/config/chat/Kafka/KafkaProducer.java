package com.example.SucceSS.config.chat.Kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {
    /*
    private final KafkaTemplate<String, ChatDto> kafkaTemplate;
    private final String USER_TOPIC = "user-request";
    private final String AI_TOPIC = "ai-response";

    public void sendMessageToUser(ChatDto dto) {
        CompletableFuture<SendResult<String, ChatDto>> completableFuture =
                kafkaTemplate.send(AI_TOPIC, dto).toCompletableFuture();

        completableFuture
                .thenAccept(result -> log.info("Successfully sent message: {}", dto.getText()))
                .exceptionally(ex -> {
                    log.error("Failed to send message: {}", dto.getText(), ex);
                    return null;
                });
        log.info("Produce message: {}", dto.getText());
    }

    public void sendMessageToAI(ChatDto dto) {
        CompletableFuture<SendResult<String, ChatDto>> completableFuture =
                kafkaTemplate.send(USER_TOPIC, dto).toCompletableFuture();

        completableFuture
                .thenAccept(result -> log.info("Successfully sent message: {}", dto.getText()))
                .exceptionally(ex -> {
                    log.error("Failed to send message: {}", dto.getText(), ex);
                    return null;
                });
        log.info("Produce message: {}", dto.getText());
    }


     */

}
