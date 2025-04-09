package com.leocorp.springchat.user.dao;

import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    @Transactional
    int deleteByUuid(UUID uuid);
    List<UserEntity> findAll();
}
