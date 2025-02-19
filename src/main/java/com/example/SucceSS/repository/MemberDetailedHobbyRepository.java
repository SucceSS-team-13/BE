package com.example.SucceSS.repository;

import com.example.SucceSS.domain.MemberDetailedHobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberDetailedHobbyRepository extends JpaRepository<MemberDetailedHobby, Long> {
}
