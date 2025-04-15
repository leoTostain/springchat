package com.leocorp.springchat.user.dao;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

public class UserDetailsImpl implements UserDetails, CredentialsContainer {
    private final String username;
    private String password;
    private final Collection<AuthorityEntity> authorities;

    public UserDetailsImpl(UserEntity user) {
        Objects.requireNonNull(user);
        username = user.getUsername();
        password = user.getPassword();
        authorities = user.getAuthority();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities.stream().map(authorityEntity ->
            new SimpleGrantedAuthority(authorityEntity.getAuthority().name())
        ).toList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void eraseCredentials() {
        password = null; // Securely dereference the password field
    }
}
