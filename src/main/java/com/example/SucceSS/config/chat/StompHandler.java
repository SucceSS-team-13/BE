package com.example.SucceSS.config.chat;

import com.example.SucceSS.apiPayload.ApiResponse;
import com.example.SucceSS.apiPayload.status.ErrorStatus;
import com.example.SucceSS.config.security.JwtProvider;
import com.example.SucceSS.utils.JwtUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
@Slf4j
public class StompHandler implements ChannelInterceptor {

    private final JwtProvider jwtProvider;
    private final JwtUtils jwtUtils;
    private final ObjectMapper objectMapper;


    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            try {
                String token = jwtUtils.extractToken(accessor.getFirstNativeHeader("Authorization"));
                if (jwtProvider.validateToken(token)) {
                    Authentication auth = jwtProvider.getAuthentication(token);
                    accessor.getSessionAttributes().put("auth", auth);
                }
            } catch (JwtException e) {
                log.error("error setting Authentication : {}",e.getMessage());
                Throwable cause = e.getCause();
                if (cause instanceof ExpiredJwtException) {
                    return sendErrorMessage(ApiResponse.onFailure(ErrorStatus._EXPIRED_TOKEN.getCode(), ErrorStatus._EXPIRED_TOKEN.getMessage()));
                } else if (cause instanceof MalformedJwtException) {
                    return sendErrorMessage(ApiResponse.onFailure(ErrorStatus._MALFORMED.getCode(), ErrorStatus._MALFORMED.getMessage()));
                } else {
                    return sendErrorMessage(ApiResponse.onFailure(ErrorStatus._UNAUTHORIZED.getCode(), ErrorStatus._UNAUTHORIZED.getMessage()));
                }
            } catch (Exception e) {
                return sendErrorMessage(ApiResponse.onFailure(ErrorStatus._INTERNAL_SERVER_ERROR.getCode(), ErrorStatus._INTERNAL_SERVER_ERROR.getMessage()));
            }
        }
        return message;

    }

    private Message<byte[]> sendErrorMessage(ApiResponse<Void> response) {
        StompHeaderAccessor headers = StompHeaderAccessor.create(StompCommand.ERROR);
        headers.setMessage(response.getMessage());
        headers.setLeaveMutable(true);

        try {
            String json = objectMapper.writeValueAsString(response);
            return MessageBuilder.createMessage(json.getBytes(StandardCharsets.UTF_8),
                    headers.getMessageHeaders());
        } catch (JsonProcessingException ex) {
            log.error("Failed to convert ErrorResponse to JSON", ex);
            return MessageBuilder.createMessage(response.getMessage().getBytes(StandardCharsets.UTF_8),
                    headers.getMessageHeaders());
        }
    }
}
