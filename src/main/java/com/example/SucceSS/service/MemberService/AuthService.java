package com.example.SucceSS.service.MemberService;

import com.example.SucceSS.config.security.JwtProvider;
import com.example.SucceSS.domain.Member;
import com.example.SucceSS.repository.MemberDetailedHobbyRepository;
import com.example.SucceSS.repository.MemberHobbyRepository;
import com.example.SucceSS.repository.MemberRepository;
import com.example.SucceSS.web.dto.KakaoAccountDto;
import com.example.SucceSS.web.dto.LoginResponseDto;
import com.example.SucceSS.web.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {
    private final MemberRepository memberRepository;
    private final KakaoService kakaoService;
    private final JwtProvider jwtProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberHobbyRepository memberHobbyRepository;

    @Transactional
    public LoginResponseDto signIn(String code) {
        KakaoAccountDto userInfo = kakaoService.getUserInfo(code);
        Member member = findOrSaveMember(userInfo);
        // 첫 로그인이 아닌 경우에도 프로필의 변경사항 업데이트
        member.updateProfile(userInfo);

        boolean isFirstLogin = !memberHobbyRepository.existsByMemberId(member.getId());

        return LoginResponseDto.of(isFirstLogin, setAuthenticationAndGetTokens(member.getSocialId()), member);
    }

    private TokenDto setAuthenticationAndGetTokens(String socialId) {
        Authentication authentication = setAuthentication(socialId, "KAKAO");
        return jwtProvider.generateToken(authentication);
    }

    @Transactional
    public Member saveMember(KakaoAccountDto dto) {
        Member member = KakaoAccountDto.toMemberEntity(dto);
        return memberRepository.save(member);
    }


    private Authentication setAuthentication(String socialId, String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuth = new UsernamePasswordAuthenticationToken(socialId, password);
        System.out.println("done getting UsernamePasswordAuthenticationToken");
        //customUserDetails -> loadUserByUsername 호출 : 실제 검증 수행
        System.out.println(usernamePasswordAuth.getName()+", "+usernamePasswordAuth.getCredentials());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(usernamePasswordAuth);
        System.out.println("getting context");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

    private Member findOrSaveMember(KakaoAccountDto userInfo) {
        return memberRepository.findBySocialId(userInfo.getId().toString())
                .orElseGet(() -> {
                    return saveMember(userInfo);
                });
    }


}
