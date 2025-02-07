package com.example.SucceSS.config.chat.Kafka;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class KafkaConfig {

    // 토픽 이름 설정
    private final String request = "/topic/user-request";
    private final String response = "/topic/ai-response";

    // 토픽 두 개 생성
    @Bean
    public List<NewTopic> createTopic() {
        return List.of(
            new NewTopic(request, 100, (short) 1),
            new NewTopic(response, 100, (short) 1)
        );
    }
}
