package com.leocorp.springchat.user.dao;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "authority_entity", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"}), @UniqueConstraint(columnNames = {"user_id", "authority"})})
public class AuthorityEntity {
    public enum AuthorityType {
        ROLE_ADMIN, ROLE_USER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private UserEntity user;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AuthorityType authority;

    public AuthorityEntity() {}

    public AuthorityEntity(UserEntity user, AuthorityType authorityType) {
        Objects.requireNonNull(user);
        Objects.requireNonNull(authorityType);

        this.authority = authorityType;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public AuthorityType getAuthority() {
        return authority;
    }

    public void setAuthority(AuthorityType authority) {
        this.authority = authority;
    }
}
