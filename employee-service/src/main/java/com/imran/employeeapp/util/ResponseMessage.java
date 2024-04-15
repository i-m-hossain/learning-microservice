package com.imran.employeeapp.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseMessage<T> {
    private String message;
    private T data;
    public ResponseMessage(String message, T data){
        this.message= message;
        this.data=data;
    }
    public static <T> ResponseEntity<ResponseMessage<T>> success(String message, T data) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage<>(message, data));
    }

    public static <T> ResponseEntity<ResponseMessage<T>> error(String message, HttpStatus status) {
        return ResponseEntity.status(status).body(new ResponseMessage<>(message, null));
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
