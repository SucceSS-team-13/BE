package com.example.SucceSS.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
public class LoginResponseDto {
    private String accessToken;
    private String refreshToken;
    private String grantType;
    private boolean firstLogIn;

    public LoginResponseDto(boolean firstLogIn) {
        this.firstLogIn = firstLogIn;
    }

    public void setTokens(TokenDto dto) {
        this.accessToken = dto.getAccessToken();
        this.refreshToken = dto.getRefreshToken();
        this.grantType = dto.getGrantType();
    }
}
