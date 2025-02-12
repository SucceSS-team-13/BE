package com.example.SucceSS.web.controller;

import com.example.SucceSS.apiPayload.ApiResponse;
import com.example.SucceSS.apiPayload.exception.MemberNotFound;
import com.example.SucceSS.service.MemberService.AuthService;
import com.example.SucceSS.web.dto.LoginResponseDto;
import com.example.SucceSS.web.dto.TokenDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static org.springframework.http.HttpStatus.OK;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/auth")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "카카오 로그인")
    @ResponseStatus(OK)
    @GetMapping("/sign-in/kakao")
    public ResponseEntity<ApiResponse<LoginResponseDto>> signInKakao(@RequestParam("code") String code) throws IOException {
        return ResponseEntity.status(OK).body(ApiResponse.onSuccess(authService.signIn(code)));
    }

    @Operation(summary = "테스트")
    @GetMapping("/sign-in/test")
    public ResponseEntity<ApiResponse<String>> test(@RequestParam("code") String code) throws IOException {
        return ResponseEntity.status(OK).body(ApiResponse.onSuccess(code));
    }

    @Operation(summary = "토큰 테스트")
    @GetMapping("/token-test")
    public ResponseEntity<ApiResponse<String>> testToken(@RequestParam("code") String code) {
        return ResponseEntity.status(OK).body(ApiResponse.onSuccess(code));
    }

}
