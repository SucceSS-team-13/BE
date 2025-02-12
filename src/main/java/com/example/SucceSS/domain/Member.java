package com.example.SucceSS.domain;

import com.example.SucceSS.domain.common.BaseEntity;
import com.example.SucceSS.domain.common.Role;
import com.example.SucceSS.domain.enums.*;
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

    /*@Id
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

    // Hobbies 컬렉션 필드 추가
    @ElementCollection(targetClass = Hobby.class)
    @CollectionTable(name = "member_hobby", joinColumns = @JoinColumn(name = "member_id"))
    @Enumerated(EnumType.STRING)
    private List<Hobby> hobby = new ArrayList<>();

    // Detailed Hobbies 컬렉션 필드 추가
    @ElementCollection(targetClass = DetailedHobby.class)
    @CollectionTable(name = "member_detailed_hobby", joinColumns = @JoinColumn(name = "member_id"))
    @Enumerated(EnumType.STRING)
    private List<DetailedHobby> detailedHobby = new ArrayList<>();*/

}
