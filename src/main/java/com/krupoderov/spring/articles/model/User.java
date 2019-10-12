package com.krupoderov.spring.articles.model;

import com.krupoderov.spring.articles.utils.Constants;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

/**
 *  В данном классе описывается модель(пользователь),
 *  которая будет помещена в базу данных
 *
 * @version 1.0
 * @author Krupoderov Mikhail
 */
@Entity
@Data
@Table(name = "usr")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Поле не может быть пустым")
    @Size(min = Constants.USERNAME_LENGTH_MIN, max = Constants.USERNAME_LENGTH_MAX)
    private String username;

    @NotBlank(message = "Поле не может быть пустым")
    @Size(min = Constants.PASSWORD_LENGTH_MIN, max = Constants.PASSWORD_LENGTH_MAX)
    private String password;
    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }
}
