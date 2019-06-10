package com.krupoderov.spring.articles.controller;

import com.krupoderov.spring.articles.model.User;
import com.krupoderov.spring.articles.repo.UserRepo;
import com.krupoderov.spring.articles.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @Autowired
    UserService userService;

    /**
     * Список пользователей
     *
     * @param model модель для хранения пользователей из базы данных
     * @return userList.html
     */
    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "userList";
    }

    @GetMapping("profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());

        return "profile";
    }

    @PostMapping("profile")
    public String updateProfile(
            @AuthenticationPrincipal User user,
            @RequestParam String password
    ) {
        userService.updateProfile(user, password);

        return "redirect:/user/profile";
    }
}
