package com.example.SucceSS.domain;

import com.example.SucceSS.domain.enums.DetailedHobby;
import com.example.SucceSS.domain.enums.Hobby;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    // @Column(nullable = false)
    private Hobby hobby;

    @Enumerated(EnumType.STRING)
    // @Column(nullable = false)
    private DetailedHobby detailedHobby;
}
