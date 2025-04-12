package com.leocorp.springchat;


import com.leocorp.springchat.user.UserMapper;
import com.leocorp.springchat.user.dao.UserEntity;
import com.leocorp.springchat.user.dto.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class UserMapperTest {

    private static boolean UserEntityEqualsUser(UserEntity userEntity, User user) {
        return user.uuid().equals(userEntity.getUuid()) && user.username().equals(userEntity.getUsername());
    }

    @Test
    public void mapUserEntityToUser() {
        var userEntity = new UserEntity("user");
        var user = UserMapper.UserEntityToUser(userEntity);
        assert(UserEntityEqualsUser(userEntity, user));
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
