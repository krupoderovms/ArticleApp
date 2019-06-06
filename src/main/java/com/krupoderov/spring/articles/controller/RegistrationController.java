package com.krupoderov.spring.articles.controller;

import com.krupoderov.spring.articles.model.Role;
import com.krupoderov.spring.articles.model.User;
import com.krupoderov.spring.articles.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

/**
 * Класс, представляющий собой контроллер, в котором происходит отображение формы регистрации пользователей
 *
 * @version 1.0
 * @author Krupoderov Mikhail
 */
@Controller
public class RegistrationController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Страница регистрации
     * @return registration.html
     */
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    /**
     * Форма регистрации нового пользователя
     * @param user Принимает пользователя из базы данных
     * @param model Модель, для хранения сообщения о том, что пользователь уже существует в базе
     * @return Переадресация на страницу логина
     */
    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.addAttribute("message", "Пользователь уже существует!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);

        return "redirect:/login";
    }
}
