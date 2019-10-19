package com.krupoderov.spring.articles.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * Роли пользователей
 *
 * @version 1.1
 * @author Krupoderov Mikhail
 */
@Entity
@Data
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String name;

    @Column(unique = true)
    @NotEmpty
    private String authority;

    @Override
    public String toString() {
        return name;
    }
}
