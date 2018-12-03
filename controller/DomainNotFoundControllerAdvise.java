package com.waaproject.registrationsystem.controller;

import com.waaproject.registrationsystem.exception.BlockNotFoundException;
import com.waaproject.registrationsystem.exception.SectionNotFoundException;
import com.waaproject.registrationsystem.exception.StudentNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DomainNotFoundControllerAdvise {


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = StudentNotFoundException.class)
    protected ResponseEntity<String> handleStudentException(RuntimeException ex) {
        String body = ex.getMessage();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("reason", "ACCESS DENIED");
        return new ResponseEntity<>(body, httpHeaders, HttpStatus.NOT_FOUND);

    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = SectionNotFoundException.class)
    protected ResponseEntity<String> handleSectionException(RuntimeException ex) {
        String body = ex.getMessage();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("reason", "ACCESS DENIED");
        return new ResponseEntity<>(body, httpHeaders, HttpStatus.NOT_FOUND);
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = BlockNotFoundException.class)
    protected ResponseEntity<String> handleBlockException(RuntimeException ex) {
        String body = ex.getMessage();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("reason", "ACCESS DENIED");
        return new ResponseEntity<>(body, httpHeaders, HttpStatus.NOT_FOUND);
    }
}
