package com.waaproject.registrationsystem.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

public class CheckUserAuthentication implements HandlerInterceptor {
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse arg1, Object arg2) throws Exception {
        Principal principal = request.getUserPrincipal();
        String userEmail  = null;
        if(request.isUserInRole("ROLE_ADMIN") || request.isUserInRole("ROLE_STUDENT")) {
            userEmail = principal.getName();
        }
        request.setAttribute("userEmail", userEmail);
        return true;
    }
}
