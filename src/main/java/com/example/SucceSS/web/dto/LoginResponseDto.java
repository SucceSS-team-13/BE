package com.example.SucceSS.web.dto;

import com.example.SucceSS.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    private String accessToken;
    private String refreshToken;
    private String grantType;
    private boolean firstLogIn;
    private String nickname;
    private String profileImgUrl;

    public static LoginResponseDto of(boolean isFirstLogin, TokenDto tokens, Member member) {
        return LoginResponseDto.builder()
                .firstLogIn(isFirstLogin)
                .accessToken(tokens.getAccessToken())
                .refreshToken(tokens.getRefreshToken())
                .grantType(tokens.getGrantType())
                .nickname(member.getNickname())
                .profileImgUrl(member.getProfileImgUrl())
                .build();
    }
}
