package com.leocorp.springchat.user.dto;

import java.util.Objects;

public record UserCredential(String username, String password) {
    public UserCredential {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        if (username.length() < 3 || username.length() > 16) {
            throw new IllegalArgumentException("Username must be between 3 and 16 characters");
        } else if (password.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters");
        }
    }
}
