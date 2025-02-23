package com.example.SucceSS.web.dto;

import com.example.SucceSS.domain.enums.*;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("ageGroup")
    private AgeGroup ageGroup;
    @JsonProperty("location")
    private String location;
    @JsonProperty("personalityEnergy")
    private PersonalityEnergy personalityEnergy;
    @JsonProperty("personalityJudgement")
    private PersonalityJudgement personalityJudgement;
    private List<MemberHobbyDto> hobbies = new ArrayList<>();
}
