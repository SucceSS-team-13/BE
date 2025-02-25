package com.example.SucceSS.web.dto;

import com.example.SucceSS.domain.Member;
import com.example.SucceSS.domain.common.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoAccountDto {
    //회원 번호
    @JsonProperty("id")
    public Long id;

    @JsonProperty("connected_at")
    public Date connectedAt;

    @JsonProperty("kakao_account")
    public KakaoAccount kakaoAccount;


    @Getter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class KakaoAccount {
        //프로필 : 닉네임
        @JsonProperty("profile")
        public Profile profile;

        @Getter
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public class Profile {
            //닉네임
            @JsonProperty("nickname")
            public String nickName;

            @JsonProperty("profile_image_url")
            public String profileImgUrl;
        }

    }

    public static Member toMemberEntity(KakaoAccountDto dto) {
        return Member.builder()
                .socialId(dto.getId().toString())
                .nickname(dto.getKakaoAccount().getProfile().getNickName())
                .profileImgUrl(dto.getKakaoAccount().getProfile().getProfileImgUrl())
                .userRole(Role.ROLE_USER)
                .build();
    }

}
