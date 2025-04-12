package com.leocorp.springchat.user;

import com.leocorp.springchat.user.dto.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Create a new user from a username
     * @param username the user's username
     * @return the user
     */
    @Operation(summary = "Create a new user from a username")
    @ApiResponse(responseCode = "201", description = "User created")
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/addUser")
    public User addUser(String username) {
        return UserMapper.UserEntityToUser(userService.createUser(username));
    }

    /**
     * Remove a user from its uuid
     * @param uuid the user's uuid
     */
    @Operation(summary = "Remove a user from its uuid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User has been deleted"),
            @ApiResponse(responseCode = "404", description = "No user found for the given uuid", content = @Content),
            @ApiResponse(responseCode = "400", description = "Given uuid does not follow uuid standards",
                    content = @Content)})
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("/removeUser")
    public void removeUser(UUID uuid) {
        userService.removeUser(uuid);
    }

    /**
     * Get a user
     * @param uuid the user's uuid
     * @return the user
     */
    @Operation(summary = "Retrieve a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User retrieved"),
            @ApiResponse(responseCode = "404", description = "No user found for the given uuid", content = @Content)})
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/user")
    public User getUser(UUID uuid) {
        return UserMapper.UserEntityToUser(userService.getUser(uuid));
    }

    /**
     * Get a list of users
     * @return A list of all the users
     */
    @Operation(summary = "Retrieve a list of all users")
    @ApiResponse(responseCode = "200", description = "All users retrieved")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/users")
    public List<User> getUsers() {
        return UserMapper.UserEntityListToUserList(userService.getUsers());
    }
}