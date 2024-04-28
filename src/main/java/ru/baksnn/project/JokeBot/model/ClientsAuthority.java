package ru.baksnn.project.JokeBot.model;

import org.springframework.security.core.GrantedAuthority;

public enum ClientsAuthority implements GrantedAuthority {
    DEFAULT_USER,
    MODERATOR,
    ADMIN;

    @Override
    public String getAuthority() {
        return this.name();
    }
}