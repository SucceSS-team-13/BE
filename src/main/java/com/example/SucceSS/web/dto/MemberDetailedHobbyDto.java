package com.example.SucceSS.web.dto;

import com.example.SucceSS.domain.enums.DetailedHobby;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDetailedHobbyDto {
    private DetailedHobby detailedHobby;
}
