package com.krupoderov.spring.articles.service;

import com.krupoderov.spring.articles.model.User;
import com.krupoderov.spring.articles.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Класс, представляющий собой сервис(компонент),
 * в котором мы описываем необходимые нам методы для работы со моделью User
 *
 * @version 1.0
 * @author Krupoderov Mikhail
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    /**
     * Метод для поиска пользователя по имени
     * @param username имя пользователя
     * @return возвращает найденного в базе пользователя
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public void updateProfile(User user, String password) {
        if (!StringUtils.isEmpty(password)) {
            user.setPassword(passwordEncoder.encode(password));
        }

        userRepo.save(user);
    }
}
