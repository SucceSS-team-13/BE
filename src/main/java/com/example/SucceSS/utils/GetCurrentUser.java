package com.example.SucceSS.utils;

import com.example.SucceSS.apiPayload.exception.MemberNotFound;
import com.example.SucceSS.config.security.JwtProvider;
import com.example.SucceSS.domain.Member;
import com.example.SucceSS.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GetCurrentUser {

    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;

    public Member getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return memberRepository.findBySocialId(authentication.getName())
                .orElseThrow(MemberNotFound::new);
    }

    public Member getCurrentUserByAuth(Authentication auth) {
        return memberRepository.findBySocialId(auth.getName())
                .orElseThrow(MemberNotFound::new);
    }

    public Member getCurrentUserByAccessor(SimpMessageHeaderAccessor accessor) {
        Authentication auth = (Authentication) accessor.getSessionAttributes().get("auth");
        return memberRepository.findBySocialId(auth.getName())
                .orElseThrow(MemberNotFound::new);
    }

}
