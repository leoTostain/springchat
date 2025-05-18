package com.leocorp.springchat.user;

import com.leocorp.springchat.user.dto.UserCredential;
import com.leocorp.springchat.user.dto.UserPrivateInfo;
import com.leocorp.springchat.user.dto.UserPublicInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Create a new user from a username
     * @param credential the user's credentials
     * @return the user
     */
    @Operation(summary = "Create a new user from a username")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created"),
            @ApiResponse(responseCode = "400", description = "Credentials does not follow the criteria",
            content = @Content)})
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/register")
    public UserPrivateInfo register(@Valid @NotNull @RequestBody UserCredential credential) {
        return UserMapper.UserEntityToUserPrivateInfo(userService.createUser(credential));
    }

    /**
     * Remove a user from its uuid
     * @param uuid the user's uuid
     */
    @Operation(summary = "Remove a user from its uuid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User has been deleted"),
            @ApiResponse(responseCode = "401", description = "Authentication failed", content = @Content),
            @ApiResponse(responseCode = "404", description = "No user found for the given uuid", content = @Content),
            @ApiResponse(responseCode = "400", description = "The given uuid does not follow uuid standards",
                    content = @Content)})
//    @SecurityRequirement(name = "basicAuth")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("/removeUser")
    public void removeUser(UUID uuid) {
        userService.removeUser(uuid);
    }

    /**
     * Get a user info
     * @return the user private info
     */
    @Operation(summary = "Get the info of the current authenticated user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User retrieved"),
            @ApiResponse(responseCode = "401", description = "Not authenticated", content = @Content)})
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/userInfo")
    public UserPrivateInfo getAuthenticatedUser() {
        return UserMapper.UserEntityToUserPrivateInfo(userService.getCurrentUser());
    }

    /**
     * Get a user
     * @param username the user's username
     * @return the user
     */
    @Operation(summary = "Retrieve a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User retrieved"),
            @ApiResponse(responseCode = "404", description = "No user found for the given username", content = @Content)})
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/user")
    public UserPublicInfo getUser(String username) {
        return UserMapper.UserEntityToUserPublicInfo(userService.getUser(username));
    }

    /**
     * Get a list of users
     * @return A list of all the users
     */
    @Operation(summary = "Retrieve a list of all users")
    @ApiResponse(responseCode = "200", description = "All users retrieved")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/users")
    public List<UserPublicInfo> getUsers() {
        return UserMapper.UserEntityListToUserPublicInfoList(userService.getUsers());
    }
}