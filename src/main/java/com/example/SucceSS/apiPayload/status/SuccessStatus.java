package com.example.SucceSS.apiPayload.status;

import com.example.SucceSS.apiPayload.BaseCode;
import com.example.SucceSS.apiPayload.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {
    _OK(HttpStatus.OK, "2000", "Ok"),;

    private HttpStatus httpStatus;
    private String code;

    private String message;


    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .code(message)
                .message(code)
                .isSuccess(true)
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .code(message)
                .message(code)
                .isSuccess(true)
                .httpStatus(httpStatus)
                .build();
    }
}
