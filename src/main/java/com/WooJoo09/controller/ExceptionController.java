package com.WooJoo09.controller;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> ServerException2(Exception e) {
        e.printStackTrace();
        Map<String, String> result = new HashMap<>();
        result.put("result", "ServerError");
        return new ResponseEntity(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> MissingRequestHeaderException(Exception e) {
        e.printStackTrace();
        Map<String, String> result = new HashMap<>();
        result.put("result", "MissingRequestHeaderException");
        return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnsupportedJwtException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> UnsupportedJwtException(Exception e) {
        e.printStackTrace();
        Map<String, String> result = new HashMap<>();
        result.put("result", "UnsupportedJwtException");
        return new ResponseEntity(result, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MalformedJwtException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> MalformedJwtException(Exception e) {
        e.printStackTrace();
        Map<String, String> result = new HashMap<>();
        result.put("result", "MalformedJwtException");
        return new ResponseEntity(result, HttpStatus.PAYMENT_REQUIRED);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> ExpiredJwtException(Exception e) {
        e.printStackTrace();
        Map<String, String> result = new HashMap<>();
        result.put("result", "ExpiredJwtException");
        return new ResponseEntity(result, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(SignatureException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> SignatureException(Exception e) {
        e.printStackTrace();
        Map<String, String> result = new HashMap<>();
        result.put("result", "SignatureException");
        return new ResponseEntity(result, HttpStatus.OK);
    }
}