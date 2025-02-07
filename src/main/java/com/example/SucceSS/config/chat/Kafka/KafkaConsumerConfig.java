package com.example.SucceSS.config.chat.Kafka;

import com.example.SucceSS.web.dto.ChatDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    private Map<String, Object> consumerConfigurations(String groupId) {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        configs.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        configs.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        return configs;
    }
    // Consumer 생성 - user request Consumer
    @Bean
    public ConsumerFactory<String, ChatDto> userRequestConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                consumerConfigurations("user-request-group"),
                new StringDeserializer(),
                new ErrorHandlingDeserializer<>(new JsonDeserializer<>(ChatDto.class))
        );
    }
    // Kafka 리스너 만들고 관리 - user request Consumer
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ChatDto> userRequestKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ChatDto> kafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        kafkaListenerContainerFactory.setConsumerFactory(userRequestConsumerFactory());
        return kafkaListenerContainerFactory;
    }
    // Consumer 생성 - ai response Consumer
    @Bean
    public ConsumerFactory<String, ChatDto> aiResponseConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                consumerConfigurations("ai-response-group"),
                new StringDeserializer(),
                new ErrorHandlingDeserializer<>(new JsonDeserializer<>(ChatDto.class))
        );
    }
    // Kafka 리스너 만들고 관리 - ai response Consumer
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ChatDto> aiResponseKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ChatDto> kafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        kafkaListenerContainerFactory.setConsumerFactory(aiResponseConsumerFactory());
        return kafkaListenerContainerFactory;
    }
}
