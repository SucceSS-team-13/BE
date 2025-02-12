package com.example.SucceSS.web.dto;

import com.example.SucceSS.domain.enums.AgeGroup;
import com.example.SucceSS.domain.enums.Hobby;
import com.example.SucceSS.domain.enums.PersonalityEnergy;
import com.example.SucceSS.domain.enums.PersonalityJudgement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseDto {
    private Long memberId;
    private String nickname;
    private AgeGroup age;
    private String location;
    private PersonalityEnergy personalityType_energy;
    private PersonalityJudgement personalityType_judgement;
    private Hobby hobby;
}
