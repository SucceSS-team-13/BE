package com.example.SucceSS.web.dto;

import com.example.SucceSS.domain.enums.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequestDto {
    // private String nickname;  // kakaoNickname 대신 nickname 사용
    private AgeGroup ageGroup;
    private String location;
    private PersonalityEnergy personalityEnergy;
    private PersonalityJudgement personalityJudgement;
    private Hobby hobby;
    private DetailedHobby detailedHobby;
}
