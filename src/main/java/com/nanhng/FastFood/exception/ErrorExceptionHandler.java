package com.nanhng.FastFood.exception;

import com.nanhng.FastFood.dto.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ErrorExceptionHandler {

    @ExceptionHandler(LovelyException.class)
    public ResponseEntity<?> handleLovelyException(LovelyException e) {
        log.error("Handle Lovely Exception", e);
        return new ResponseEntity<>(new BaseResponse<>(e.getData(),e.getMessage(),e.getStatus().value()),e.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException exc) {
        log.error("handleValidationExceptions:", exc);
        Map<String, Object> errors = new HashMap<>();
        exc.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(new BaseResponse<>(errors, " Thiếu thông tin ",HttpStatus.BAD_REQUEST.value()));
    }
}
