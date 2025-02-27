package com.example.SucceSS.domain;

import com.example.SucceSS.domain.common.BaseEntity;
import com.example.SucceSS.domain.common.Role;
import com.example.SucceSS.domain.enums.*;
import com.example.SucceSS.web.dto.KakaoAccountDto;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "nickname")
    private String nickname;

    @Column(name="profileImgUrl")
    private String profileImgUrl;

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
    private PersonalityJudgement personalityJudgement;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberHobby> memberHobbies = new ArrayList<>();

    public void updateProfile(KakaoAccountDto userInfo) {
        this.nickname = userInfo.getKakaoAccount().getProfile().getNickName();
        this.profileImgUrl = userInfo.getKakaoAccount().getProfile().getProfileImgUrl();
    }

}
