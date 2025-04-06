package com.leocorp.springchat;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    public record User(UUID uuid, String name) {}
    private final List<User> users = new ArrayList<>();

    public void createUser(String name) {
        users.add(new User(UUID.randomUUID(), name));
    }

    public List<User> getUsers() {
        return List.copyOf(users);
    }
}
