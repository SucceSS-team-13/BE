package com.example.SucceSS.service.MemberService;

import com.example.SucceSS.config.security.JwtProvider;
import com.example.SucceSS.domain.Member;
import com.example.SucceSS.domain.enums.PersonalityEnergy;
import com.example.SucceSS.domain.enums.PersonalityJudgement;
import com.example.SucceSS.repository.MemberRepository;
import com.example.SucceSS.web.dto.MemberRequestDto;


import com.example.SucceSS.repository.MemberRepository;
import com.example.SucceSS.web.dto.ChatRoomResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    private final JwtProvider jwtProvider;

    // 회원 정보 입력 + 결과 호출 로직 구현
    public MemberService(MemberRepository memberRepository, JwtProvider jwtProvider) {
        this.memberRepository = memberRepository;
        this.jwtProvider = jwtProvider;
    }

    public Long updateMember(@PathVariable Long id, MemberRequestDto requestDto) {
        // id로 해당 회원 찾기
        Optional<Member> optionalMember = memberRepository.findById(id);

        if (optionalMember.isEmpty()) {
            throw new RuntimeException("회원 정보를 찾을 수 없습니다.");
        }

        Member member = optionalMember.get();

        // 수정할 값만 변경
        member.setAgeGroup(requestDto.getAgeGroup());
        member.setLocation(requestDto.getLocation());
        member.setPersonalityEnergy(requestDto.getPersonalityEnergy());
        member.setPersonalityJudegement(requestDto.getPersonalityJudgement());
        member.setHobby(requestDto.getHobby());
        member.setDetailedHobby(requestDto.getDetailedHobby());

        // 수정된 정보를 DB에 저장
        return memberRepository.save(member).getId();
    }

    public String generateAnalysisMessage(Long id) {
        Optional<Member> optionalProfile = memberRepository.findById(id);

        if (optionalProfile.isEmpty()) {
            return "사용자 정보를 찾을 수 없습니다.";
        }

        Member member = optionalProfile.get();
        StringBuilder message = new StringBuilder();
        message.append("안녕하세요, ").append(member.getNickname()).append("님! 성향 분석 결과를 알려드릴게요.\n\n");

        // 성향 분석 문구 생성
        if (member.getPersonalityEnergy() == PersonalityEnergy.INTROVERT) {
            message.append("조용한 환경에서 깊이 있는 생각을 하는 것을 선호하시는 내향적인 성향을 가지고 계시네요.");
        } else {
            message.append("활발한 환경에서 사람들과 교류하는 것을 좋아하시는 외향적인 성향이시네요.");
        }

        if (member.getPersonalityJudegement() == PersonalityJudgement.LOGICAL) {
            message.append("특히 논리적인 사고를 바탕으로 결정을 내리시는 특징이 있습니다.\n\n");
        } else {
            message.append("특히 사람들의 감정을 잘 이해하고 공감하는 성향이 강한 특징이 있습니다.\n\n");
        }

        message.append("취미로 ").append(member.getHobby().getDescription()).append("에 관심이 있으시고, ");
        message.append("특히 ").append(member.getDetailedHobby().getDescription()).append("에 푹 빠져 계시는군요! 이러한 취미 활동은 당신의 내면을 더욱 풍요롭게 만들어주고 있을 거에요.\n\n");

        message.append("자신만의 페이스를 유지하면서 차근차근 성장해 나가시면 좋을 것 같아요. 혹시 고민이 있으시다면 언제든 저에게 이야기해 주세요.");

        return message.toString();
    }


}
