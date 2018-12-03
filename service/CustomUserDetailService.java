package com.waaproject.registrationsystem.service;

import com.waaproject.registrationsystem.domain.Role;
import com.waaproject.registrationsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
@Qualifier
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Fetches the db to find a user with the given username(i.e. email address)
     * If it finds a user, it constructs a UserDetails object by fetching the roles.
     *
     * @param email
     * @return
     * @throws UsernameNotFoundException if user cannot be found.
     */

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {


        return userRepository
                .findByEmail(email)
                .map(u -> new User(
                        u.getEmail(),
                        u.getPassword(),
                        u.isActive(),
                        u.isActive(),
                        u.isActive(),
                        u.isActive(),
                        AuthorityUtils.createAuthorityList(
                                new HashSet<>(Arrays.asList(u.getRole()))
                                        .stream()
                                        .map(r -> "ROLE_" + r.getName().toUpperCase())
                                        .collect(Collectors.toList())
                                        .toArray(new String[]{}))))
                .orElseThrow(() -> new UsernameNotFoundException("No user with "
                        + "the name " + email + "was found in the database"));


    }


}
