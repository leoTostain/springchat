package com.leocorp.springchat;


import com.leocorp.springchat.user.UserMapper;
import com.leocorp.springchat.user.dao.UserEntity;
import com.leocorp.springchat.user.dto.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserMapperTest {

    private static boolean UserEntityEqualsUser(UserEntity userEntity, User user) {
        return user.uuid().equals(userEntity.getUuid()) && user.username().equals(userEntity.getUsername());
    }

    @Test
    void mapUserEntityToUserThrowsNPEWhenNull() {
        assertThatThrownBy(() -> UserMapper.UserEntityToUser(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void mapUserEntityToUser() {
        var userEntity = new UserEntity("user");
        var user = UserMapper.UserEntityToUser(userEntity);
        assert(UserEntityEqualsUser(userEntity, user));
    }

    @Test
    void mapUserEntityListToUserListThrowsNPEWhenNull() {
        assertThatThrownBy(() -> UserMapper.UserEntityListToUserList(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void mapUserEntityListToUserList() {
        var userEntities = new ArrayList<UserEntity>();
        userEntities.add(new UserEntity("user1"));
        userEntities.add(new UserEntity("user2"));
        userEntities.add(new UserEntity("user3"));
        var users = UserMapper.UserEntityListToUserList(userEntities);

        for (int i = 0; i < users.size(); i++) {
            assert(UserEntityEqualsUser(userEntities.get(i), users.get(i)));
        }
    }
}
