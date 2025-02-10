package com.example.SucceSS.domain;

import com.example.SucceSS.domain.common.BaseEntity;
import com.example.SucceSS.domain.common.Role;
import com.example.SucceSS.domain.enums.*;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member")
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;

    @Column(name="social_id", unique = true)
    private String socialId;

    // @NotNull
    @Column(name = "nickname")
    private String nickname;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name="user_role")
    private Role userRole;

    @Enumerated(EnumType.STRING)
    private AgeGroup ageGroup;

    private String location;

    @Enumerated(EnumType.STRING)
    private PersonalityEnergy personalityEnergy;

    @Enumerated(EnumType.STRING)
    private PersonalityJudgement personalityJudegement;

    @Enumerated(EnumType.STRING)
    private Hobby hobby;

    @Enumerated(EnumType.STRING)
    private DetailedHobby detailedHobby;

}
