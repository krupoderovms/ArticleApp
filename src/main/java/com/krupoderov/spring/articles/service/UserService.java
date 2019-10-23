package com.krupoderov.spring.articles.service;

import com.krupoderov.spring.articles.model.Role;
import com.krupoderov.spring.articles.model.User;
import com.krupoderov.spring.articles.repository.RoleRepository;
import com.krupoderov.spring.articles.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

/**
 * Класс, представляющий собой сервис(компонент),
 * в котором мы описываем необходимые нам методы для работы со моделью User
 *
 * @version 1.0
 * @author Krupoderov Mikhail
 */
@Service
public class UserService implements UserDetailsService {

    private static final long ID_ROLE_FOR_NEW_USER = 1L;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    /**
     * Метод для поиска пользователя по имени
     * @param username имя пользователя
     * @return возвращает найденного в базе пользователя
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("Can't find user with username " + username);
        }
        return user;
    }

    public void updateProfile(User user, String password) {
        if (!StringUtils.isEmpty(password)) {
            user.setPassword(passwordEncoder.encode(password));
        }
        userRepository.save(user);
    }

    public User getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = null;
        if (userDetails instanceof User) {
            user = (User) userDetails;
        }
        return user;
    }

    public void sighupUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findById(ID_ROLE_FOR_NEW_USER).orElse(null);
        user.setRoles(Collections.singleton(userRole));
        userRepository.save(user);
    }

    public boolean isCurrentUserId(Long id) {
        User user = getCurrentUser();
        return (Objects.nonNull(user) && (user.getId().equals(id)));
    }

    public boolean hasRole(String role) {
        Collection<GrantedAuthority> authorities =
                (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        boolean hasRole = false;
        for (GrantedAuthority authority : authorities) {
            hasRole = authority.getAuthority().equals(role);
            if (hasRole) {
                break;
            }
        }
        return hasRole;
    }
}
