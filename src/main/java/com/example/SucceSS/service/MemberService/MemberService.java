package com.example.SucceSS.service.MemberService;

import com.example.SucceSS.domain.Member;
import com.example.SucceSS.domain.MemberHobby;
import com.example.SucceSS.domain.enums.DetailedHobby;
import com.example.SucceSS.domain.enums.Hobby;
import com.example.SucceSS.domain.enums.PersonalityEnergy;
import com.example.SucceSS.domain.enums.PersonalityJudgement;
import com.example.SucceSS.repository.MemberHobbyRepository;
import com.example.SucceSS.repository.MemberRepository;
import com.example.SucceSS.web.dto.MemberRequestDto;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberHobbyRepository memberHobbyRepository;

    public MemberService(MemberRepository memberRepository, MemberHobbyRepository memberHobbyRepository) {
        this.memberRepository = memberRepository;
        this.memberHobbyRepository = memberHobbyRepository;
    }

    @Transactional
    public Long updateMember(@PathVariable Long id, MemberRequestDto requestDto) {
        Optional<Member> optionalMember = memberRepository.findById(id);

        if (optionalMember.isEmpty()) {
            throw new RuntimeException("회원 정보를 찾을 수 없습니다.");
        }

        Member member = optionalMember.get();

        member.setAgeGroup(requestDto.getAgeGroup());
        member.setLocation(requestDto.getLocation());
        member.setPersonalityEnergy(requestDto.getPersonalityEnergy());
        member.setPersonalityJudgement(requestDto.getPersonalityJudgement());

        // 기존 Hobby 데이터 삭제 후 새로 저장
        memberHobbyRepository.deleteByMember(member);
        List<MemberHobby> newHobbies = requestDto.getHobbies().stream()
                .map(dto -> new MemberHobby(null, member, dto.getHobby(), dto.getDetailedHobby()))
                .collect(Collectors.toList());

        member.getMemberHobbies().clear();
        member.getMemberHobbies().addAll(newHobbies);

        memberHobbyRepository.saveAll(newHobbies);
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

        if (member.getPersonalityEnergy() == PersonalityEnergy.INTROVERT) {
            message.append("조용한 환경에서 깊이 있는 생각을 하는 것을 선호하시는 내향적인 성향을 가지고 계시네요.");
        } else {
            message.append("활발한 환경에서 사람들과 교류하는 것을 좋아하시는 외향적인 성향이시네요.");
        }

        if (member.getPersonalityJudgement() == PersonalityJudgement.LOGICAL) {
            message.append("특히 논리적인 사고를 바탕으로 결정을 내리시는 특징이 있습니다.\n\n");
        } else {
            message.append("특히 사람들의 감정을 잘 이해하고 공감하는 성향이 강한 특징이 있습니다.\n\n");
        }

        // 변경된 Hobby, DetailedHobby 가져오는 코드
        message.append("취미로 ").append(member.getMemberHobbies().stream()
                        .map(MemberHobby::getHobby)
                        .distinct()
                        .map(Hobby::getDescription)
                        .collect(Collectors.joining(", ")))
                .append("에 관심이 있으시고, ");

        message.append("특히 ").append(member.getMemberHobbies().stream()
                        .map(MemberHobby::getDetailedHobby)
                        .distinct()
                        .map(DetailedHobby::getDescription)
                        .collect(Collectors.joining(", ")))
                .append("에 푹 빠져 계시는군요! ");

        message.append("자신만의 페이스를 유지하면서 차근차근 성장해 나가시면 좋을 것 같아요. 혹시 고민이 있으시다면 언제든 저에게 이야기해 주세요.");

        return message.toString();
    }

}
