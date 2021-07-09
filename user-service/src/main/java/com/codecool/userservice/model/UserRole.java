package com.codecool.userservice.model;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN; // may wish to add ADMIN and other roles in the future

    @Override
    public String getAuthority() {
        return this.toString();
    }
}
