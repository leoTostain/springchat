package com.leocorp.springchat.user;

import com.leocorp.springchat.user.dto.User;
import io.swagger.v3.oas.annotations.Operation;
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
     * @param name String
     * @return UUID
     */
    @Operation(summary = "Create a new user from a username")
    @ApiResponse(responseCode = "201", description = "User created")
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/addUser")
    public User addUser(String name) {
        return UserMapper.UserEntityToUser(userService.createUser(name));
    }

    /**
     * Remove a user from its uuid
     * @param uuid String
     */
    @Operation(summary = "Remove a user from its uuid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User has been deleted"),
            @ApiResponse(responseCode = "404", description = "No user found for the given uuid"),
            @ApiResponse(responseCode = "400", description = "Given uuid does not follow uuid standards")})
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("/removeUser")
    public void removeUser(String uuid) {
        userService.removeUser(UUID.fromString(uuid));
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