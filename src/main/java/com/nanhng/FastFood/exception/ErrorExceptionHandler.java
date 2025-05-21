package com.nanhng.FastFood.exception;

import com.nanhng.FastFood.dto.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ErrorExceptionHandler {

    @ExceptionHandler(LovelyException.class)
    public ResponseEntity<?> handleLovelyException(LovelyException e) {
        log.error("Handle Lovely Exception", e);
        return new ResponseEntity<>(new BaseResponse<>(e.getData(),e.getMessage(),e.getStatus().value()),e.getStatus());
    }
}
