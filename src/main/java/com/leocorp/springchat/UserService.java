package com.leocorp.springchat;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    public record User(UUID uuid, String name) {}
    private final List<User> users = new ArrayList<>();

    public UUID createUser(String name) {
        var uuid = UUID.randomUUID();
        users.add(new User(uuid, name));
        return uuid;
    }

    public void removeUser(UUID uuid) {
        if (!users.removeIf(user -> user.uuid.equals(uuid))) {
            throw new UserNotFoundException("User not found for the given uuid");
        }
    }

    public List<User> getUsers() {
        return List.copyOf(users);
    }
}
