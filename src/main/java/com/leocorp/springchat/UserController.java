package com.leocorp.springchat;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public void addUser(String name) {
        userService.createUser(name);
    }

    /**
     * Remove a user from its uuid
     * @param uuid String
     */
    @DeleteMapping("/removeUser")
    public boolean removeUser(String uuid) {
        return userService.removeUser(UUID.fromString(uuid));
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