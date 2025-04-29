package com.leocorp.springchat.user;

import com.leocorp.springchat.user.dao.AuthorityEntity;
import com.leocorp.springchat.user.dao.UserEntity;
import com.leocorp.springchat.user.dao.UserRepository;
import com.leocorp.springchat.user.dto.UserCredential;
import com.leocorp.springchat.user.exception.UserAlreadyExistException;
import com.leocorp.springchat.user.exception.UserNotAuthenticatedException;
import com.leocorp.springchat.user.exception.UserNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Create a user
     * @param credential The user's credentials
     * @return a new instance of UserEntity
     * @throws NullPointerException if username is null
     * @throws UserAlreadyExistException if a user with the same username already exist
     */
    public UserEntity createUser(UserCredential credential) {
        Objects.requireNonNull(credential);

        try {
            if (getUser(credential.username()) != null) {
                throw new UserAlreadyExistException();
            }
        } catch (UserNotFoundException ignored) {}

        var newUser = new UserEntity(credential.username(), passwordEncoder.encode(credential.password()));
        newUser.setAuthority(new AuthorityEntity(newUser, AuthorityEntity.AuthorityType.ROLE_USER));
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
     * Get the current authenticated user
     * @return The UserEntity of the user
     * @throws UserNotAuthenticatedException if the no user is currently authenticated
     */
    public UserEntity getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new UserNotAuthenticatedException();
        }

        return userRepository.getByUsername(authentication.getName());
    }

    /**
     * Get a user from its uuid
     * @param username the user's username
     * @return The UserEntity of the user
     * @throws NullPointerException if username is null
     * @throws UserNotFoundException if no user is found for the given username
     */
    public UserEntity getUser(String username) {
        Objects.requireNonNull(username);
        var user = userRepository.getByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User not found for the given username");
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
