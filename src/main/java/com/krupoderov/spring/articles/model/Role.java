package com.krupoderov.spring.articles.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Роли пользователей
 *
 * @version 1.0
 * @author Krupoderov Mikhail
 */
public enum Role implements GrantedAuthority {
    USER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
