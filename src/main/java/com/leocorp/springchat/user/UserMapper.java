package com.leocorp.springchat.user;

import com.leocorp.springchat.user.dao.UserEntity;
import com.leocorp.springchat.user.dto.User;

import java.util.List;

public class UserMapper {
    public static User UserEntityToUser(UserEntity userEntity) {
        return new User(userEntity.getUuid(), userEntity.getUsername());
    }

    public static List<User> UserEntityListToUserList(List<UserEntity> userEntity) {
        return userEntity.stream()
                .map(UserMapper::UserEntityToUser)
                .toList();
    }
}
