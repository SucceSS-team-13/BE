package com.example.SucceSS.web.dto;

import com.example.SucceSS.domain.enums.DetailedHobby;
import com.example.SucceSS.domain.enums.Hobby;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberHobbyDto {
    private Hobby hobby;
    private DetailedHobby detailedHobby;
}
