package com.leocorp.springchat;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Create a new user from a name
     * @param name String
     */
    @PostMapping("/addUser")
    public void addUsers(String name) {
        userService.createUser(name);
    }

    /**
     * Get a list of users
     * @return A list of all the users
     */
    @GetMapping("/users")
    public List<UserService.User> getUsers() {
        return userService.getUsers();
    }
}