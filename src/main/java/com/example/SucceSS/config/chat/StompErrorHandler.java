package com.example.SucceSS.config.chat;

import com.example.SucceSS.apiPayload.ApiResponse;
import com.example.SucceSS.apiPayload.exception.NoAuthorizationHeaderException;
import com.example.SucceSS.apiPayload.exception.NoBearerException;
import com.example.SucceSS.apiPayload.status.ErrorStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.socket.messaging.StompSubProtocolErrorHandler;

import java.nio.charset.StandardCharsets;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class StompErrorHandler extends StompSubProtocolErrorHandler {
    private final ObjectMapper objectMapper;
    @Override
    public Message<byte[]> handleClientMessageProcessingError(Message<byte[]> clientMessage, Throwable ex) {
        if (ex instanceof MessageDeliveryException) {
            Throwable cause = ex.getCause();
            log.error("Error occurred ", cause);
            if (cause instanceof ExpiredJwtException) {
                return sendErrorMessage(ApiResponse.onFailure(ErrorStatus._EXPIRED_TOKEN.getCode(), ErrorStatus._EXPIRED_TOKEN.getMessage()), ex);
            } else if (cause instanceof MalformedJwtException) {
                return sendErrorMessage(ApiResponse.onFailure(ErrorStatus._MALFORMED.getCode(), ErrorStatus._MALFORMED.getMessage()), ex);
            } else {
                return sendErrorMessage(ApiResponse.onFailure(ErrorStatus._INTERNAL_SERVER_ERROR.getCode(), ErrorStatus._INTERNAL_SERVER_ERROR.getMessage()), ex);
            }
        }
        return super.handleClientMessageProcessingError(clientMessage, ex);
    }
    private Message<byte[]> sendErrorMessage(ApiResponse<Void> response, Throwable e) {
        log.error("Failed to convert ErrorResponse to JSON", e);
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
