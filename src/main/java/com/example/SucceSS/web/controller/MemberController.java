package com.example.SucceSS.web.controller;

import com.example.SucceSS.apiPayload.ApiResponse;
import com.example.SucceSS.service.MemberService.MemberService;
import com.example.SucceSS.web.dto.MemberRequestDto;
import com.example.SucceSS.web.dto.MemberResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PatchMapping("/profile/update/{id}")
    public ResponseEntity<ApiResponse<MemberResponseDto>> updateMember(@PathVariable Long id, @RequestBody MemberRequestDto requestDto) {
        ApiResponse<MemberResponseDto> response = memberService.updateMember(id, requestDto);
        return ResponseEntity.ok(response);
    }

    // 사용자 정보 조회 및 결과 반환
    @GetMapping("/profile/{id}")
    public ResponseEntity<ApiResponse<MemberResponseDto>> getMemberAnalysis(@PathVariable Long id) {
        ApiResponse<MemberResponseDto> response = memberService.generateMemberAnalysis(id);
        return ResponseEntity.ok(response);
    }
}
