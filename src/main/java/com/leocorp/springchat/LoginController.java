package com.leocorp.springchat;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Operation(summary = "Login a user")
    @PostMapping("login")
    public void login(@RequestParam String username, @RequestParam String password) {}

    @Operation(summary = "Logout a user")
    @ApiResponse(responseCode = "204", description = "Logout successful")
    @PostMapping("logout")
    public void logout() {}
}
