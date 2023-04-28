package com.blogs.Blog_app_apis.Exceptions;

import com.blogs.Blog_app_apis.Payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException ex) {
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message, false);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> error = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((err -> {
            String fieldName = ((FieldError) err).getField();
            String message = err.getDefaultMessage();
            error.put(fieldName, message);
        }));
        return new ResponseEntity<Map<String, String>>(error, HttpStatus.BAD_REQUEST);
    }
}
