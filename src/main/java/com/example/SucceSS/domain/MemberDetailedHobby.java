package com.example.SucceSS.domain;

import com.example.SucceSS.domain.enums.DetailedHobby;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDetailedHobby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_detailed_hobby_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_hobby_id", nullable = false)
    private MemberHobby memberHobby;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DetailedHobby detailedHobby;
}
