package com.waaproject.registrationsystem.controller;

import com.waaproject.registrationsystem.service.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SeedController {

    private final SeedService seedService;

    @Autowired
    public SeedController(SeedService seedService) {
        this.seedService = seedService;
    }

    @GetMapping(value = "/loadUser")
    public String loadUserData(){

        List<Integer> primaryKeys = this.seedService.loadUserData();
        System.out.println(primaryKeys);
        return "loginForm";

    }

    @GetMapping(value = "/loadData")
    public String loadData(){
        seedService.loadData();

        return "LoginForm";

    }
}
