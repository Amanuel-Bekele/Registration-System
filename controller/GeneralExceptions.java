package com.waaproject.registrationsystem.controller;

import com.waaproject.registrationsystem.exception.AccessDeniedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GeneralExceptions {
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Illegal request, please verify your payload")
    public ResponseEntity<String> handleClientErrors(Exception ex) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("reason", "ACCESS DENIED");
        return new ResponseEntity<>("Denied", httpHeaders, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ResponseEntity<String> handle(Exception ex) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("reason", "ACCESS DENIED");
        return new ResponseEntity<>(ex.getMessage(), httpHeaders, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal server error")
    public ResponseEntity<String> handleServerErrors(Exception ex) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("reason", "ACCESS DENIED");
        return new ResponseEntity<>(ex.getMessage(), httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RequestRejectedException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Bad Request")
    public ResponseEntity<String> handleRejection(RequestRejectedException ex) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("reason", "Request Not Understood");
        return new ResponseEntity<>(ex.getMessage(), httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
