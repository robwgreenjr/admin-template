package com.greenbeard.scheduling.users.controller;

import com.greenbeard.scheduling.users.model.User;
import com.greenbeard.scheduling.users.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public List<User> getUserList() {
        return userService.findAll();
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/user/login/{login}")
    public User getUserByLogin(@PathVariable String login) {
        return userService.findByLogin(login);
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User theUser) {
        userService.save(theUser);
        return theUser;
    }

    @PutMapping("/user/{id}")
    public User updateUser(@RequestBody User theUser, @PathVariable Long id) {
        userService.update(theUser, id);
        return theUser;
    }

    @DeleteMapping("/user/{id}")
    public boolean deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return true;
    }
}
