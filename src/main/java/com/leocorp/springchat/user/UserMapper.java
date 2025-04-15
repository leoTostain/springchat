package com.leocorp.springchat.user;

import com.leocorp.springchat.user.dao.UserEntity;
import com.leocorp.springchat.user.dto.UserPrivateInfo;
import com.leocorp.springchat.user.dto.UserPublicInfo;

import java.util.List;
import java.util.Objects;

public class UserMapper {
    public static UserPrivateInfo UserEntityToUserPrivateInfo(UserEntity userEntity) {
        Objects.requireNonNull(userEntity);
        return new UserPrivateInfo(userEntity.getUuid(), userEntity.getUsername());
    }

    public static UserPublicInfo UserEntityToUserPublicInfo(UserEntity userEntity) {
        Objects.requireNonNull(userEntity);
        return new UserPublicInfo(userEntity.getUsername());
    }

    public static List<UserPublicInfo> UserEntityListToUserPublicInfoList(List<UserEntity> userEntity) {
        Objects.requireNonNull(userEntity);
        return userEntity.stream()
                .map(UserMapper::UserEntityToUserPublicInfo)
                .toList();
    }
}
