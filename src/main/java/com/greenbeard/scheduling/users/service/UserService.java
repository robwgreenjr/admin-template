package com.greenbeard.scheduling.users.service;

import com.greenbeard.scheduling.users.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(Long theId);

    User findByLogin(String theLogin);

    void save(User theUser);

    User update(User theUser, Long id);

    void deleteById(Long theId);
}
