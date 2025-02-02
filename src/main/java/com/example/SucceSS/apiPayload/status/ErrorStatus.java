package com.example.SucceSS.apiPayload.status;

import com.example.SucceSS.apiPayload.BaseCode;
import com.example.SucceSS.apiPayload.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorStatus implements BaseCode {
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),
    _NOT_FOUND(HttpStatus.NOT_FOUND,"COMMON404","해당 경로에 맞는 리소스를 찾을 수 없습니다"),
    _EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED,"EXPIRE_401","토큰이 만료되었습니다."),
    _SIGNATURE(HttpStatus.UNAUTHORIZED,"SIGNATURE_401","잘못된 서명입니다"),
    _MALFORMED(HttpStatus.UNAUTHORIZED,"MALFORMED_401","잘못된 형식입니다"),
    _NO_HEADER(HttpStatus.UNAUTHORIZED,"NO_HEADER_401","인증 헤더가 존재하지 않습니다"),
    _BEARER(HttpStatus.UNAUTHORIZED,"NO_BEARER_401","Bearer 형식이 올바르지 않습니다."),
    _LOGOUT(HttpStatus.UNAUTHORIZED,"LOGOUT_401","로그아웃 된 토큰입니다");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}
