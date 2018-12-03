package com.waaproject.registrationsystem.controller;


import com.waaproject.registrationsystem.domain.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DBTestRestController {

    @GetMapping("/data")
    public Student getData(){
        return new Student();
    }
}
