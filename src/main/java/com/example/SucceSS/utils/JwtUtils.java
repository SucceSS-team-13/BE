package com.example.SucceSS.utils;


import com.example.SucceSS.apiPayload.exception.NoAuthorizationHeaderException;
import com.example.SucceSS.apiPayload.exception.NoBearerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
public class JwtUtils {

    public String extractToken(String token) {
        if(!StringUtils.hasText(token)) throw new NoAuthorizationHeaderException();
        if(!token.startsWith("Bearer")) throw new NoBearerException();

        return token.substring(7);
    }
}
