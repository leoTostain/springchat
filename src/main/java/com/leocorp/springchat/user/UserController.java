package com.leocorp.springchat.user;

import com.leocorp.springchat.user.dto.UserCredential;
import com.leocorp.springchat.user.dto.UserPrivateInfo;
import com.leocorp.springchat.user.dto.UserPublicInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public UserController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Login a user
     * @param credential the user's credentials
     */
    @Operation(summary = "Login a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful"),
            @ApiResponse(responseCode = "400", description = "Credentials does not follow the criteria",
                    content = @Content)})
    @ResponseStatus(code = HttpStatus.OK)
    @PostMapping("/login")
    public void login(UserCredential credential) {
        Authentication authenticationRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(credential.username(), credential.password());
        this.authenticationManager.authenticate(authenticationRequest);
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
    @PostMapping("/signIn")
    public UserPrivateInfo signIn(UserCredential credential) {
        return UserMapper.UserEntityToUserPrivateInfo(userService.createUser(credential));
    }

    /**
     * Remove a user from its uuid
     * @param uuid the user's uuid
     */
    @Operation(summary = "Remove a user from its uuid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User has been deleted"),
            @ApiResponse(responseCode = "404", description = "No user found for the given uuid", content = @Content),
            @ApiResponse(responseCode = "400", description = "The given uuid does not follow uuid standards",
                    content = @Content)})
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("/removeUser")
    public void removeUser(UUID uuid) {
        userService.removeUser(uuid);
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