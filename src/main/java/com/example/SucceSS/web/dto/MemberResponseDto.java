package com.example.SucceSS.web.dto;

import com.example.SucceSS.domain.enums.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    private List<Hobby> hobby;
    private List<DetailedHobby> detailed_hobby;
}
