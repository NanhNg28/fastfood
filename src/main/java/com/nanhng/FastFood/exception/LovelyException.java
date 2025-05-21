package com.nanhng.FastFood.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class LovelyException extends RuntimeException {

    private HttpStatus status = HttpStatus.BAD_REQUEST;

    private Object data;

    public LovelyException(String message) {
        super(message);
    }

    public LovelyException(String message, HttpStatus status) {
      super(message);
      this.status = status;
    }

    public LovelyException(Object data, String message) {
        super(message);
        this.data = data;
    }

    public LovelyException(Object data, String message, HttpStatus status) {
        super(message);
        this.status = status;
        this.data = data;
    }


}
