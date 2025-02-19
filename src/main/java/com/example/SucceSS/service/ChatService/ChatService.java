package com.example.SucceSS.service.ChatService;

import com.example.SucceSS.apiPayload.exception.ChatRoomNotFound;
import com.example.SucceSS.domain.Chat;
import com.example.SucceSS.domain.ChatRoom;
import com.example.SucceSS.domain.Member;
import com.example.SucceSS.domain.enums.PersonalityJudgement;
import com.example.SucceSS.repository.ChatRepository;
import com.example.SucceSS.repository.ChatRoomRepository;
import com.example.SucceSS.web.dto.ChatResponseDto;
import com.example.SucceSS.web.dto.ChatRequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final ChatRoomRepository chatRoomRepository;
    private static final String USER = "user";
    private static final String LUMI = "lumi";

    @Value("${spring.huggingface.api.url}")
    private String API_URL;

    @Value("${spring.huggingface.api.key}")
    private String API_KEY;

    @Transactional
    public ChatResponseDto userSendChat(ChatRequestDto chatDto, Member member) throws IOException {
        updateChatRoomInfos(chatRoomRepository.findByChatRoomId(chatDto.getChatRoomId())
                .orElseThrow(ChatRoomNotFound::new), chatDto);

        Chat newChat = chatRepository.save(Chat.of(chatDto,member.getId()));

        Long chatRoomId = chatDto.getChatRoomId();
        String message = chatDto.getText();
        String mode = null;
        if (member.getPersonalityJudgement() == PersonalityJudgement.EMOTIONAL)
            mode = "emotion";
        else if (member.getPersonalityJudgement() == PersonalityJudgement.LOGICAL)
            mode = "rational";
        else
            log.error("잘못된 감정 형식입니다.");
        
        String response = generateAiResponse(message, mode);
        Chat aiResponse = chatRepository.save(Chat.of(chatRoomId, member.getId(), response, LUMI));

        return ChatResponseDto.fromWithoutLocation(aiResponse);
    }

    private String generateAiResponse(String userInput, String mode) throws IOException {
        // AI 모델에 전달
        String prompt = String.format("<%s><usr>%s</usr><sys>", mode, userInput);
        ObjectMapper objectMapper = new ObjectMapper();
        // Hugging Face API 요청을 위한 payload 생성
        String jsonPayload = createPayload(prompt, objectMapper);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + API_KEY);

        HttpEntity<String> requestEntity = new HttpEntity<>(jsonPayload, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(API_URL, HttpMethod.POST, requestEntity, String.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String response = responseEntity.getBody();
            JsonNode node = objectMapper.readTree(response);
            node = node.get(0).get("generated_text");
            if (node != null) {
                response = node.asText().replace(prompt, "").replace("<sys>", "").replace("</sys>", "").trim();
                int lastIndex = response.lastIndexOf(".");
                if (lastIndex != -1) { response = response.substring(0, lastIndex + 1); }
            }
            else { return "no generated_text"; }
            return response;
        }
        else { return "Error : " + responseEntity.getStatusCode(); }
    }

    private static String createPayload(String prompt, ObjectMapper objectMapper) throws JsonProcessingException {
        Map<String, Object> generationParams = new HashMap<>();
        generationParams.put("max_new_tokens", 128);
        generationParams.put("temperature", 0.7);
        generationParams.put("top_p", 0.9);
        generationParams.put("top_k", 50);
        generationParams.put("repetition_penalty", 1.2);
        generationParams.put("do_sample", true);

        Map<String, Object> options = new HashMap<>();
        options.put( "wait_for_model", true);

        Map<String, Object> payload = new HashMap<>();
        payload.put("inputs", prompt);
        payload.put("parameters", generationParams);
        payload.put("options", options);

        return objectMapper.writeValueAsString(payload);
    }

    public void updateChatRoomInfos(ChatRoom chatRoom, ChatRequestDto chatDto) {
        // 첫 채팅 발생하면 채팅방 제목 변경
        if(!chatRepository.existsByChatRoomId(chatRoom.getChatRoomId())) {
            chatRoom.updateChatRoomTitle(chatDto.getText());
        }
        chatRoom.setUpdatedDate(LocalDateTime.now());
    }
}