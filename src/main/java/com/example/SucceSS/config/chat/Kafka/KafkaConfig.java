package com.example.SucceSS.config.chat.Kafka;

import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {
    /*

    @Value("${spring.kafka.bootstrap-servers}")
    private static String bootstrapServers;


     // 토픽 이름 설정
    private final String request = "user-request";
    private final String response = "ai-response";

    // 토픽 두 개 생성
    @Bean
    public List<NewTopic> createTopic() {
        return List.of(
            new NewTopic(request, 100, (short) 1),
            new NewTopic(response, 100, (short) 1)
        );
    }

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        try (KafkaAdminClient adminClient = (KafkaAdminClient) KafkaAdminClient.create(props)) {
            List<NewTopic> topics = List.of(
                    new NewTopic("user-request", 100, (short) 1),
                    new NewTopic("ai-response", 100, (short) 1)
            );
            adminClient.createTopics(topics);
            System.out.println(adminClient.listTopics());
        }
    }

     */
}
