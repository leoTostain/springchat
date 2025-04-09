package com.leocorp.springchat.user.dto;

import java.util.Objects;
import java.util.UUID;

public record User(UUID uuid, String name) {
    public User {
        Objects.requireNonNull(uuid);
        Objects.requireNonNull(name);
        if (name.length() < 3) {
            throw new IllegalArgumentException("Username must be at least 3 characters long");
        }
    }
}
