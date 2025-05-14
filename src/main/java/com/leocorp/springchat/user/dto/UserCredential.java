package com.leocorp.springchat.user.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserCredential(
        @NotNull @Size(min = 3, max = 16, message = "Username must be between 3 and 16 characters") String username,
        @NotNull @Size(min = 6, message = "Password must be at least 6 characters") String password
) {}
