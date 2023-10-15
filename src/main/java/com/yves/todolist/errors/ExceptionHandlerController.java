package com.yves.todolist.errors;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandlerController {
    public ResponseEntity<?> handlerHttpMessageReadableException(HttpMessageNotReadableException err) {
        return ResponseEntity.badRequest().body(err.getMessage());
    }
}
