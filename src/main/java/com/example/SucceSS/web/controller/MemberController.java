package com.example.SucceSS.web.controller;

import com.example.SucceSS.apiPayload.ApiResponse;
import com.example.SucceSS.service.MemberService.MemberService;
import com.example.SucceSS.web.dto.MemberRequestDto;
import com.example.SucceSS.web.dto.MemberResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;
    private static final Logger log = LoggerFactory.getLogger(MemberController.class);

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PatchMapping("/profile/update")
    public ResponseEntity<ApiResponse<MemberResponseDto>> updateMember(@RequestBody MemberRequestDto requestDto) {
        log.info("Received Request Body: {}", requestDto);
        ApiResponse<MemberResponseDto> response = memberService.updateMember(requestDto);
        return ResponseEntity.ok(response);
    }

    // 사용자 정보 조회 및 결과 반환
    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<MemberResponseDto>> getMemberAnalysis() {
        ApiResponse<MemberResponseDto> response = memberService.generateMemberAnalysis();
        return ResponseEntity.ok(response);
    }
}
