package com.nanhng.FastFood.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.java.accessibility.util.Translator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseResponse <T> implements Serializable {

    private int code = HttpStatus.OK.value();
    private T data;
    private String massage = "label success";
    long totalRecords;
    int currentPage;

    public BaseResponse(String massage) {
        this.massage = massage;
    }

    public BaseResponse( String massage, int code) {
        this.code = code;
        this.massage = massage;
    }

    public BaseResponse(T data, String massage, int code) {
        this.code = code;
        this.data = data;
        this.massage = massage;
    }

    public BaseResponse(T data, String massage) {
        this.data = data;
        this.massage = massage;
    }

    public BaseResponse(T data, long totalRecords, int currentPage) {
        this.data = data;
        this.totalRecords = totalRecords;
        this.currentPage = currentPage;
    }
}
