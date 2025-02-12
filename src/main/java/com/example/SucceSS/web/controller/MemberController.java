package com.example.SucceSS.web.controller;

import com.example.SucceSS.service.MemberService.MemberService;
import com.example.SucceSS.web.dto.MemberRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PatchMapping("/profile/update/{id}")
    public String saveMember(@PathVariable Long id, @RequestBody MemberRequestDto requestDto) {
        memberService.updateMember(id, requestDto);
        return "사용자 정보가 저장되었습니다.";
    }
    // 사용자 정보 조회 및 결과 반환
    @GetMapping("/profile/{id}")
    public String getMemberAnalysis(@PathVariable Long id) {
        return memberService.generateAnalysisMessage(id);
    }
    /*private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 설문 조사 결과 저장
    @PatchMapping("/profile/update/{id}")
    public String saveMember(@PathVariable Long id, @RequestBody MemberRequestDto requestDto) {
        Long userId = memberService.updateMember(id, requestDto);
        return "사용자 정보가 저장되었습니다.";
    }

    // 사용자 정보 조회 및 결과 반환
    @GetMapping("/profile/{id}")
    public String getMemberAnalysis(@PathVariable Long id) {
        return memberService.generateAnalysisMessage(id);
    }*/
}
