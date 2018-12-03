package com.waaproject.registrationsystem.controller;

import com.waaproject.registrationsystem.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
public class LoginController {

    @Autowired
    CustomUserDetailService customUserDetailService;

    @GetMapping(value = "/")
    public String redirectToLogin(){
        return "redirect:/login";
    }

    @GetMapping(value = "/login")
    public String showLoginForm(){
        return "loginForm";
    }
    @GetMapping(value = "/access-denied")
    public String accessDenied(){
        return "access-denied";
    }

    @GetMapping(value = "/login", params = "error")
    public String loginError(Model model){
        model.addAttribute("loginError", true);
        return "loginForm";
    }
    @GetMapping(value = "/login", params = "logout")
    public String logOut(Model model){
        model.addAttribute("loggedOut", true);
        return "loginForm";
    }

    @GetMapping(value = "/student")

    public String showStudentPage(){

        return "studentPage";
    }

    @GetMapping(value = "/admin")

    public String showAdminPage(){

        return "adminPage";
    }
}
