package com.example.SucceSS.config.chat.Kafka;

import org.springframework.context.annotation.Configuration;
@Configuration
public class KafkaProducerConfig {
    /*
    // 브로커 주소
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ProducerFactory<String, ChatDto> producerFactory() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configs);
    }

    @Bean
    public KafkaTemplate<String, ChatDto> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

     */
}
