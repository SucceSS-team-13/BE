package com.example.SucceSS.config.security;

import com.example.SucceSS.apiPayload.ApiResponse;
import com.example.SucceSS.apiPayload.status.ErrorStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);
    private final static String EXCEPTION = "exception";
    private final static String LOGOUT = "logout";
    private final static String MALFORMED = "malformed";
    private final static String EXPIRED = "expired";
    private final static String HEADER = "header";
    private final static String BEARER = "bearer";
    private final static String SIGNATURE = "signature";

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        String exception = (String)request.getAttribute(EXCEPTION);
        response.setContentType("application/json; charset=UTF-8");
        logger.error("인증 실패 - 예외 발생: {}, 원인: {}", request.getAttribute("EXCEPTION"), authException.getMessage(), authException);

        if(exception!=null) {
            switch (exception) {
                case EXPIRED -> setResponse(response, ErrorStatus._EXPIRED_TOKEN.getCode(), "만료된 토큰입니다");
                case MALFORMED -> setResponse(response, ErrorStatus._MALFORMED.getCode(), "잘못된 형식의 토큰입니다");
                case HEADER -> setResponse(response, ErrorStatus._NO_HEADER.getCode(), "Authorization 헤더가 존재하지 않습니다.");
                case LOGOUT -> setResponse(response, ErrorStatus._LOGOUT.getCode(), "로그아웃 처리된 토큰입니다.");
                case BEARER -> setResponse(response, ErrorStatus._BEARER.getCode(), "Bearer 형식이 잘못됐습니다.");
                case SIGNATURE -> setResponse(response, ErrorStatus._SIGNATURE.getCode(), "JWT signature가 유효하지 않습니다.");
            }
        }
    }
    public void setResponse(HttpServletResponse response,String code,String msg) throws IOException {
        ApiResponse<Void> errorResponse = ApiResponse.onFailure(code, msg);

        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }
}
