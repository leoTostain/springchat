package com.leocorp.springchat.user;

import com.leocorp.springchat.user.dao.UserRepository;
import com.leocorp.springchat.user.dto.UserCredential;
import com.leocorp.springchat.user.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {
        assertThat(userService).isNotNull();
    }

    @Test
    void addUserThrowsNullPointerExceptionIfUsernameIsNull() {
        assertThatThrownBy(() -> userService.createUser(new UserCredential(null, null)))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void addUserUsernameIsEmpty() {
        assertThatThrownBy(() -> userService.createUser(new UserCredential("", "")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void addUserReturnIsNotNull() {
        assertThat(userService.createUser(new UserCredential("user5", "password"))).isNotNull();
    }

    @Test
    void addUserIsCreated() {
        userService.createUser(new UserCredential("user5", "password5"));
        assertThat(userRepository.findAll()).hasSize(5);
    }

    @Test
    void getUserThrowsNullPointerExceptionIfUsernameIsNull() {
        assertThatThrownBy(() -> userService.getUser(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    void getUserThrowsUserNotFoundExceptionIfUsernameIsWrong() {
        assertThatThrownBy(() -> userService.getUser("notAnUser")).isInstanceOf(UserNotFoundException.class);
    }

    @Test
    void getUserHasGoodUsername() {
        assert(userService.getUser("user2")
                .getUsername()
                .equals("user2"));
    }

    @Test
    @Sql({"/remove_users.sql"})
    void getAllUsersWithEmptyDatabaseReturnEmptyList() {
        assertThat(userService.getUsers()).isEmpty();
    }

    @Test
    void getAllUsersHasCorrectSize() {
        assertThat(userService.getUsers()).hasSize(4);
    }

    @Test
    void removeUserThrowsNullPointerExceptionIfUsernameIsNull() {
        assertThatThrownBy(() -> userService.removeUser(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    void removeUserThrowsUserNotFoundExceptionIfUsernameIsEmpty() {
        assertThatThrownBy(() -> userService.removeUser(UUID.randomUUID())).isInstanceOf(UserNotFoundException.class);
    }

    @Test
    void removeUserDatabaseUpdated() {
        userService.removeUser(UUID.fromString("957cbde3-7673-4bcc-8aa5-e6f861087a83"));
        assertThat(userRepository.findAll()).hasSize(3);
    }
}
