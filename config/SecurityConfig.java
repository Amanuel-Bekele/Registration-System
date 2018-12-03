package com.waaproject.registrationsystem.config;

import com.waaproject.registrationsystem.repository.UserRepository;
import com.waaproject.registrationsystem.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;


@Configuration
@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthSuccessHandler handler;

    private final CustomUserDetailService userDetailsService;


    @Autowired
    public SecurityConfig(AuthSuccessHandler handler,
                          CustomUserDetailService userDetailsService) {
        this.handler = handler;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()

                .antMatchers("/", "/register").permitAll()
//                .antMatchers("/**").permitAll()
                .antMatchers("/student/**").hasRole("STUDENT")
                .antMatchers("/courses/**").hasRole("STUDENT")
                .antMatchers("/admin/**").hasRole("ADMIN")

                .and()
                .formLogin()
                .successHandler(handler)
                .loginPage("/login")
                .loginProcessingUrl("/authUser").permitAll()

                .and()
                .logout()
                .deleteCookies("JSESSIONID")
                .permitAll()

//                .and()
//                .rememberMe()
//                .rememberMeCookieName("remember-me")
//                .tokenValiditySeconds(24 * 60 * 60) // 1 day!
//                .tokenRepository(tokenRepository)

                .and()
                .exceptionHandling()
                .accessDeniedPage("/access-denied");

//                .and()
//                .csrf().disabled();
    }
}
