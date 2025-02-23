package com.example.SucceSS.domain;

import com.example.SucceSS.domain.enums.DetailedHobby;
import com.example.SucceSS.domain.enums.Hobby;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberHobby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    //@Column(nullable = false)
    private Hobby hobby;

    // MemberDetailedHobby와 1:N 관계
    @OneToMany(mappedBy = "memberHobby", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberDetailedHobby> detailedHobbies = new ArrayList<>();
}
