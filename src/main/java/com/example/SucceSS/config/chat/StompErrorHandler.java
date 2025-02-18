package com.example.SucceSS.config.chat;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;


@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class StompErrorHandler  {
    /*
    private final ObjectMapper objectMapper;
    @Override
    public Message<byte[]> handleClientMessageProcessingError(Message<byte[]> clientMessage, Throwable ex) {
        if (ex instanceof MessageDeliveryException) {
            Throwable cause = ex.getCause();
            log.error("Error occurred ", cause);
            return sendErrorMessage(ApiResponse.onFailure(ErrorStatus._INTERNAL_SERVER_ERROR.getCode(), ErrorStatus._INTERNAL_SERVER_ERROR.getMessage()), ex);
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

     */
}
