package com.leocorp.springchat.user;


import com.leocorp.springchat.user.dao.UserEntity;
import com.leocorp.springchat.user.dto.UserPrivateInfo;
import com.leocorp.springchat.user.dto.UserPublicInfo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserMapperTest {

    private static boolean UserEntityEqualsUserPrivateInfo(UserEntity userEntity, UserPrivateInfo user) {
        return user.uuid().equals(userEntity.getUuid()) && user.username().equals(userEntity.getUsername());
    }

    private static boolean UserEntityEqualsUserPublicInfo(UserEntity userEntity, UserPublicInfo user) {
        return user.username().equals(userEntity.getUsername());
    }

    @Test
    void mapUserEntityToUserThrowsNPEWhenNull() {
        assertThatThrownBy(() -> UserMapper.UserEntityToUserPrivateInfo(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void mapUserEntityToUser() {
        var userEntity = new UserEntity("user", "password");
        var user = UserMapper.UserEntityToUserPrivateInfo(userEntity);
        assert(UserEntityEqualsUserPrivateInfo(userEntity, user));
    }

    @Test
    void mapUserEntityListToUserPublicInfoListThrowsNPEWhenNull() {
        assertThatThrownBy(() -> UserMapper.UserEntityListToUserPublicInfoList(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void mapUserEntityListToUserList() {
        var userEntities = new ArrayList<UserEntity>();
        userEntities.add(new UserEntity("user1", "password1"));
        userEntities.add(new UserEntity("user2", "password2"));
        userEntities.add(new UserEntity("user3", "password3"));
        var users = UserMapper.UserEntityListToUserPublicInfoList(userEntities);

        for (int i = 0; i < users.size(); i++) {
            assert(UserEntityEqualsUserPublicInfo(userEntities.get(i), users.get(i)));
        }
    }
}
