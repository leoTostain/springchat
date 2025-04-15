package com.leocorp.springchat.user.dao;

import io.micrometer.common.lang.NonNullApi;
import jakarta.annotation.Nullable;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@NonNullApi
@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long>, CrudRepository<UserEntity, Long> {
    @Transactional
    int deleteByUuid(UUID uuid);
    List<UserEntity> findAll();
    Page<UserEntity> findAll(Pageable pageable);
    Iterable<UserEntity> findAll(Sort sort);
    @Nullable
    UserEntity getByUsername(String username);
}
