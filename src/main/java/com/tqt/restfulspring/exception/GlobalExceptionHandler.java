package com.tqt.restfulspring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameExistsException.class)
    public ResponseEntity<Map<String, Object>> handleUsernameExists(
            UsernameExistsException ex) {

        Map<String, Object> body = new HashMap<>();
        body.put("errorCode", "USERNAME_EXISTS");
        body.put("message", ex.getMessage());
        body.put("status", 409);
        body.put("timestamp", LocalDateTime.now());

        return ResponseEntity
                .status(HttpStatus.CONFLICT) // 409
                .body(body);
    }
}
