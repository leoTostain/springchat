package com.leocorp.springchat.user;

import com.leocorp.springchat.user.dao.UserEntity;
import com.leocorp.springchat.user.dto.User;

import java.util.List;
import java.util.Objects;

public class UserMapper {
    public static User UserEntityToUser(UserEntity userEntity) {
        Objects.requireNonNull(userEntity);
        return new User(userEntity.getUuid(), userEntity.getUsername());
    }

    public static List<User> UserEntityListToUserList(List<UserEntity> userEntity) {
        Objects.requireNonNull(userEntity);
        return userEntity.stream()
                .map(UserMapper::UserEntityToUser)
                .toList();
    }
}
