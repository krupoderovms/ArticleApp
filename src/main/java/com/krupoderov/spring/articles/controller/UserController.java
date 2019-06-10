package com.krupoderov.spring.articles.controller;

import com.krupoderov.spring.articles.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Класс, представляющий собой контроллер, в котором происходит отображение списка пользователей
 *
 * @version 1.0
 * @author Krupoderov Mikhail
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepo userRepo;

    /**
     * Список пользователей
     * @param model модель для хранения пользователей из базы данных
     * @return userList.html
     */
    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "userList";
    }

}
