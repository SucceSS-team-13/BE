package com.example.SucceSS.repository;

import com.example.SucceSS.domain.Member;
import com.example.SucceSS.domain.MemberHobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberHobbyRepository extends JpaRepository<MemberHobby, Long> {
    void deleteByMember(Member member);
}
