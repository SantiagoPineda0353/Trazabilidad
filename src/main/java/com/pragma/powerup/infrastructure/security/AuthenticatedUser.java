package com.pragma.powerup.infrastructure.security;

public class AuthenticatedUser {
    private final Long id;

    public AuthenticatedUser(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
