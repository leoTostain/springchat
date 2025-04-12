package com.leocorp.springchat.user;

import com.leocorp.springchat.user.dao.UserEntity;
import com.leocorp.springchat.user.dao.UserRepository;
import com.leocorp.springchat.user.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Create a user
     * @param username The user's username
     * @return a new instance of UserEntity
     * @throws NullPointerException if username is null
     */
    public UserEntity createUser(String username) {
        Objects.requireNonNull(username);
        var newUser = new UserEntity(username);
        userRepository.save(newUser);
        return newUser;
    }

    /**
     * Remove a user
     * @param uuid the user's uuid
     * @throws NullPointerException if uuid is null
     * @throws UserNotFoundException if no user is found for the given uuid
     */
    public void removeUser(UUID uuid) {
        Objects.requireNonNull(uuid);
        if (userRepository.deleteByUuid(uuid) == 0) {
            throw new UserNotFoundException("User not found for the given uuid");
        }
    }

    /**
     * Get a user from its uuid
     * @param uuid the user's uuid
     * @return The UserEntity of the user
     * @throws NullPointerException if uuid is null
     * @throws UserNotFoundException if no user is found for the given uuid
     */
    public UserEntity getUser(UUID uuid) {
        Objects.requireNonNull(uuid);
        var user = userRepository.getByUuid(uuid);
        if (user == null) {
            throw new UserNotFoundException("User not found for the given uuid");
        }
        return user;
    }

    /**
     * Return a list of all users
     * @return the list of UserEntity
     */
    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }
}
