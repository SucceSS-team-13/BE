package com.example.SucceSS.service.ChatService;

import com.example.SucceSS.apiPayload.exception.ChatRoomNotFound;
import com.example.SucceSS.domain.Chat;
import com.example.SucceSS.domain.ChatRoom;
import com.example.SucceSS.domain.Member;
import com.example.SucceSS.domain.MemberHobby;
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
import java.util.ArrayList;
import java.util.List;
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

    @Transactional
    public ChatResponseDto userSendChat(ChatRequestDto chatDto, Member member) throws IOException {
        updateChatRoomInfos(chatRoomRepository.findByChatRoomId(chatDto.getChatRoomId())
                .orElseThrow(ChatRoomNotFound::new), chatDto);

        chatRepository.save(Chat.of(chatDto,member.getId()));

        Long chatRoomId = chatDto.getChatRoomId();
        String message = chatDto.getText();

        String createdPayload = createPayload(member, message);

        String response = generateAiResponse(createdPayload);
        Chat aiResponse = chatRepository.save(Chat.of(chatRoomId, member.getId(), response, LUMI));

        return ChatResponseDto.fromWithoutLocation(aiResponse);
    }

    private String generateAiResponse(String payload) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(API_URL, new HttpEntity<>(payload, headers), String.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String response = responseEntity.getBody();
            JsonNode node = objectMapper.readTree(response);
            return getCleanResponse(node);
        }
        else { return "Error : " + responseEntity.getStatusCode(); }
    }

    private static String getCleanResponse(JsonNode node) {
        String mode = node.get("mode").asText();
        String response = null;
        if (mode.equals("recommend") || mode.equals("chat")) {
            node = node.get("response");
            if (node != null) {
                response = node.asText().replace("\"", "").replace("<sys>", "").replace("</sys>", "").trim();
                if (mode.equals("chat")) {
                    int lastIndex = response.lastIndexOf(".");
                    if (lastIndex != -1) { response = response.substring(0, lastIndex + 1); }
                }
                return response;
            }
            else { return "no generated_text"; }
        }
        else { return "Error: 잘못된 응답 모드입니다."; }
    }

    private static String createPayload(Member member, String message) throws JsonProcessingException {
        // AI 모델에 전달할 userProfile 생성
        ObjectMapper objectMapper = new ObjectMapper();
        String mode = null;
        String modeDes = null;
        if (member.getPersonalityJudgement() == PersonalityJudgement.EMOTIONAL) {
            mode = "emotion";
            modeDes = "이성형"; }
        else if (member.getPersonalityJudgement() == PersonalityJudgement.LOGICAL) {
            mode = "rational";
            modeDes = "감정형"; }
        else
            log.error("잘못된 감정 형식입니다.");

        List<String> hobby = new ArrayList<>();
        List<String> detailedHobby = new ArrayList<>();
        List<MemberHobby> memberHobbies = member.getMemberHobbies();

        for (MemberHobby memberHobby : memberHobbies) {
            String hobbyDes = memberHobby.getHobby().getDescription();
            String detailedHobbyDes = memberHobby.getDetailedHobby().getDescription();
            if(!hobby.contains(hobbyDes))
                hobby.add(hobbyDes);
            if(!detailedHobby.contains(detailedHobbyDes))
                detailedHobby.add(detailedHobbyDes);
        }

        String energyDes = null;
        if (member.getPersonalityEnergy() != null)
            energyDes = member.getPersonalityEnergy().getDescription() + "형";
        else { log.error("잘못된 에너지 형식입니다."); }

         return objectMapper.writeValueAsString(Map.of("user_input", message, "mode", mode, "extroversion", energyDes,
                       "feeling_thinking", modeDes, "hobby", hobby, "detail_hobby", detailedHobby));
    }

    public void updateChatRoomInfos(ChatRoom chatRoom, ChatRequestDto chatDto) {
        // 첫 채팅 발생하면 채팅방 제목 변경
        if(!chatRepository.existsByChatRoomId(chatRoom.getChatRoomId())) {
            chatRoom.updateChatRoomTitle(chatDto.getText());
        }
        chatRoom.setUpdatedDate(LocalDateTime.now());
    }
}