package com.krupoderov.spring.articles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog") // адрес, по которому будет вызван контроллер
public class MainController {

    @RequestMapping
    public String mainPage(Model model) {
        model.addAttribute("message", "Hello World!");
        return "main";
    }
}
