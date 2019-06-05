package com.krupoderov.spring.articles.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Роли пользователей
 */
public enum Role implements GrantedAuthority {
    USER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
