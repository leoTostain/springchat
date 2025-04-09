package com.leocorp.springchat.user;

import com.leocorp.springchat.user.dao.UserEntity;
import com.leocorp.springchat.user.dao.UserRepository;
import com.leocorp.springchat.user.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID createUser(String name) {
        return userRepository.save(new UserEntity(name)).getUuid();
    }

    public void removeUser(UUID uuid) {
        if (userRepository.deleteByUuid(uuid) == 0) {
            throw new UserNotFoundException("User not found for the given uuid");
        }
    }

    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }
}
