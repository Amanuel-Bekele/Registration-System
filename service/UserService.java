package com.waaproject.registrationsystem.service;

import com.waaproject.registrationsystem.domain.User;

public interface UserService {

    public User save(User user);

    public User findById(Integer id);
}
