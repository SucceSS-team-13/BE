package com.example.SucceSS.apiPayload;

import com.example.SucceSS.apiPayload.exception.MemberNotFound;
import com.example.SucceSS.apiPayload.status.ErrorStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MemberNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiResponse<Void> handleMemberNotFoundException(MemberNotFound ex) {
        return ApiResponse.<Void>builder()
                .isSuccess(false)
                .code(ErrorStatus._NOT_FOUND.toString())
                .message(ex.getMessage())
                .build();
    }
}
