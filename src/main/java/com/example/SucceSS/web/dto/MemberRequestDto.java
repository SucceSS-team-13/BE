package com.example.SucceSS.web.dto;

import com.example.SucceSS.domain.enums.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequestDto {
    private AgeGroup ageGroup;
    private String location;
    private PersonalityEnergy personalityEnergy;
    private PersonalityJudgement personalityJudgement;
    private List<MemberHobbyDto> hobbies = new ArrayList<>();
}
