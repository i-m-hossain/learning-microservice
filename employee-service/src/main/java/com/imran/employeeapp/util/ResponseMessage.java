package com.imran.employeeapp.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseMessage {

    public static <T> ResponseEntity<T> createResponse(HttpStatus status, T body, String message) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("status", status.value());
        responseBody.put("message", message);
        responseBody.put("data", body);
        return ResponseEntity.status(status).body((T) responseBody);
    }

    public static <T> ResponseEntity<T> createResponse(HttpStatus status, T body) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("status", status.value());
        responseBody.put("data", body);
        return ResponseEntity.status(status).body((T)responseBody);
    }
    public static <T> ResponseEntity<T> createResponse(HttpStatus status, String message) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("status", status.value());
        responseBody.put("message", message);
        return ResponseEntity.status(status).body((T)responseBody);
    }
}
