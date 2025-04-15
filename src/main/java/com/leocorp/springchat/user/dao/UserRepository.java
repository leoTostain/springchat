package com.leocorp.springchat.user.dao;

import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    @Transactional
    int deleteByUuid(@Param("uuid") UUID uuid);
    List<UserEntity> findAll();

    UserEntity getByUuid(@Param("uuid") UUID uuid);
}
