package com.example.SucceSS.config.chat.Kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {
    /*
    private final String USER_TOPIC = "user-request";
    private final String AI_TOPIC = "ai-response";
    private final String USER_GROUP = "user-request-group";
    private final String AI_GROUP = "ai-response-group";
    private final SimpMessagingTemplate template;

    @KafkaListener(groupId = USER_GROUP ,topics=USER_TOPIC , containerFactory = "aiResponseKafkaListenerContainerFactory")
    public void listenUserChat(ChatDto chatDto){
        // 모델로 send
        try {
            template.convertAndSend("/topic/chatRoom/"+chatDto.getChatRoomId(), chatDto);
            log.info("get message: {}", chatDto.getText());
        } catch (Exception e) {
            log.error("Error sending message to WebSocket", e);
        }
    }

    @KafkaListener(groupId = AI_GROUP ,topics=AI_TOPIC, containerFactory = "userRequestKafkaListenerContainerFactory")
    public void listenAiChat(ChatDto chatDto){
        // ML에서 받은 응답 전송
        try {
            template.convertAndSend("/topic/chatRoom/"+chatDto.getChatRoomId(), chatDto);
            log.info("sent message: {}", chatDto.getText());
        } catch (Exception e) {
            log.error("Error sending message to WebSocket", e);
        }
    }
     */
}
