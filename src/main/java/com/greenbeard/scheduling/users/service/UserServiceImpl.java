package com.greenbeard.scheduling.users.service;

import com.greenbeard.scheduling.users.model.Authority;
import com.greenbeard.scheduling.users.repository.AuthorityRepository;
import com.greenbeard.scheduling.users.model.User;
import com.greenbeard.scheduling.users.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    public UserServiceImpl(UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long theId) {
        Optional<User> result = userRepository.findById(theId);

        User theUser = null;

        if (result.isPresent()) {
            theUser = result.get();
        } else {
            throw new RuntimeException("Did not find user id - " + theId);
        }

        return theUser;
    }

    @Override
    public User findByLogin(String theLogin) throws UsernameNotFoundException {
        return userRepository.findByLogin(theLogin);
    }

    @Override
    public void save(User theUser) {
        theUser.setActivated(true);
        theUser.setPassword(new BCryptPasswordEncoder().encode(theUser.getPassword()));
        Set<Authority> authorities = new HashSet<>();
        authorityRepository.findById("ADMIN").ifPresent(authorities::add);
        theUser.setAuthorities(authorities);

        userRepository.save(theUser);
    }

    @Override
    public User update(User newUser, Long theId) {
        Optional<User> oldUser = userRepository.findById(theId);

        if (oldUser.isPresent()) {
            User tempUser = oldUser.get();
            newUser.setId(tempUser.getId());
            if (newUser.getFirstName() != null) {
                newUser.setFirstName(newUser.getFirstName());
            } else {
                newUser.setFirstName(tempUser.getFirstName());
            }
            if (newUser.getLastName() != null) {
                newUser.setLastName(newUser.getLastName());
            } else {
                newUser.setLastName(tempUser.getLastName());
            }
            if (newUser.getLogin() != null) {
                newUser.setLogin(newUser.getLogin());
            } else {
                newUser.setLogin(tempUser.getLogin());
            }
            if (newUser.getEmail() != null) {
                newUser.setEmail(newUser.getEmail());
            } else {
                newUser.setEmail(tempUser.getEmail());
            }
            if (newUser.getPassword() != null) {
                newUser.setPassword(new BCryptPasswordEncoder().encode(newUser.getPassword()));
            } else {
                newUser.setPassword(tempUser.getPassword());
            }
            newUser.setActivated(true);

            userRepository.save(newUser);
        } else {
            throw new RuntimeException("Did not find user id - " + theId);
        }
        return newUser;
    }

    @Override
    public void deleteById(Long theId) {
        userRepository.deleteById(theId);
    }
}
