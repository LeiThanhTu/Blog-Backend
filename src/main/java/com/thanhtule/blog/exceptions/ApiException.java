package com.thanhtule.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ApiException extends RuntimeException{
    public ApiException(String message) {
        super(message);
    }
    public ApiException(){
        super();
    }
}
