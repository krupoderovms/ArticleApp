package com.krupoderov.spring.articles.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Класс, представляющий собой контроллер, в котором происходит отображение главной страницы
 *
 * @version 1.0
 * @author Krupoderov Mikhail
 */
@Controller
@RequestMapping("/")
public class MainController {

    /**
     * Главная страница
     * @return main.html
     */
    @RequestMapping
    public String mainPage() {
        return "main";
    }

    /**
     * Страница авторизации
     * @return login.html
     */
    @RequestMapping(value = "/login")
    public String loginPage() {
        return "login";
    }

    /**
     * Страница выхода из аккаунта
     * @param request формирует клиенту ответ c информацией о запросе, отправленным методом GET, и генерирует ответ клиенту
     * @param response определяет ответ клиенту
     * @return возвращает на страницу логина(логаута)
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/login?logout";
    }
}
