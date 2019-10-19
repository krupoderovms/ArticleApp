package com.krupoderov.spring.articles.model;

import com.krupoderov.spring.articles.utils.Constants;
import lombok.Data;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

/**
 *  В данном классе описывается модель(пользователь),
 *  которая будет помещена в базу данных
 *
 * @version 1.1
 * @author Krupoderov Mikhail
 */
@Entity
@Data
@Table(name = "usr")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Size(min = Constants.USERNAME_LENGTH_MIN, max = Constants.USERNAME_LENGTH_MAX)
    @Column(unique = true)
    private String username;

    @NotEmpty
    @Column(unique = true)
    @Email
    private String email;

    @NotEmpty
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfRegistration;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Reply> replies;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Topic> topics;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Photo photo;

    public boolean getPhotoExist() {
        return Objects.nonNull(photo);
    }

    public int getCountPosts() {
        return (topics.size() + replies.size());
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ROLE_ASSIGNMENTS",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")})
    @Setter
    private Set<Role> roles;

    public boolean getHasRoleById(Long roleId) {
        for (Role role : roles) {
            if (role.getId().equals(roleId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
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
        return true;
    }
}
