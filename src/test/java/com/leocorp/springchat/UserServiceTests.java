package com.leocorp.springchat;

import com.leocorp.springchat.user.UserService;
import com.leocorp.springchat.user.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        assertThat(userService).isNotNull();
    }

    @Test
    void addUser() {
        assertThatThrownBy(() -> userService.createUser(null)).isInstanceOf(NullPointerException.class);
        assertThat(userService.createUser("testAdd")).isNotNull();
    }

    @Test
    void getUser() {
        assertThatThrownBy(() -> userService.getUser(null)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> userService.getUser(UUID.randomUUID())).isInstanceOf(UserNotFoundException.class);
        var user = userService.createUser("testGet");
        assertThat(userService.getUser(user.getUuid())).isEqualTo(user);
    }

    @Test
    void getAllUsers() {
        assertThat(userService.getUsers()).isEmpty();
        var user1 = userService.createUser("test1");
        var user2 = userService.createUser("test2");
        var users = userService.getUsers();
        assertThat(users).hasSize(2);
        assertThat(user1).isEqualTo(users.getFirst());
        assertThat(user2).isEqualTo(users.getLast());
    }

    @Test
    void removeUser() {
        assertThatThrownBy(() -> userService.removeUser(null)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> userService.removeUser(UUID.randomUUID())).isInstanceOf(UserNotFoundException.class);
        var user = userService.createUser("testRemove");
        userService.removeUser(user.getUuid());
    }
}
