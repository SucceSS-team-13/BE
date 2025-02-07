package com.example.SucceSS.apiPayload;

import com.example.SucceSS.apiPayload.exception.MemberNotFound;
import com.example.SucceSS.apiPayload.exception.NoAuthorizationHeaderException;
import com.example.SucceSS.apiPayload.exception.NoBearerException;
import com.example.SucceSS.apiPayload.status.ErrorStatus;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.slf4j.Logger;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final  Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<ApiResponse<Void>> handleAllExceptions(Exception ex) {
        HttpStatus status = findHttpStatus(ex);
        logger.error("Exception: {} - {}", ex.getClass().getSimpleName(), ex.getMessage(), ex);

        return ResponseEntity.status(status)
                .body(ApiResponse.onFailure(status.toString(), ex.getMessage()));
    }

    private HttpStatus findHttpStatus(Exception ex) {
        ResponseStatus responseStatus = ex.getClass().getAnnotation(ResponseStatus.class);
        return (responseStatus != null) ? responseStatus.value() : HttpStatus.INTERNAL_SERVER_ERROR;
    }

}
