package com.example.SucceSS.config.chat;

import com.example.SucceSS.apiPayload.exception.NoAuthorizationHeaderException;
import com.example.SucceSS.apiPayload.exception.NoBearerException;
import com.example.SucceSS.config.security.JwtProvider;
import com.example.SucceSS.utils.JwtUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StompHandler implements ChannelInterceptor {

    private final JwtProvider jwtProvider;
    private final JwtUtils jwtUtils;


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
            } catch (Exception e) {
                throw new MessageDeliveryException(e.getMessage());  // StompErrorHandler에서 처리하도록
            }
        }
        return message;

    }
}
