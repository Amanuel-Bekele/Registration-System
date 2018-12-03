package com.waaproject.registrationsystem.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
/**
 * Redirects users to appropriate page based on their authority.
 */
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        String emAddress=authentication.getPrincipal().toString().split(";")[0].split(":")[2].trim();

        request.getSession().setAttribute("usersEmailAddress",emAddress);

        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            System.out.println("Can't redirect");
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    /**
     * Determines redirect url by getting authority.
     * @param authentication
     * @return
     */

    private String determineTargetUrl(Authentication authentication) {


        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        String url = "";

        List<String> roles = new ArrayList<>();

        for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }

        if (roles.contains("ROLE_STUDENT")) {
            url = "/courses/available";
        }
        if (roles.contains("ROLE_ADMIN")) {
            System.out.println(authentication.getPrincipal());
            url = "/admin/students/";
        }
        return url;

    }
}
