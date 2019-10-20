package com.krupoderov.spring.articles.controller;

import com.krupoderov.spring.articles.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Класс, представляющий собой контроллер, в котором происходит отображение главной страницы
 *
 * @version 1.0
 * @author Krupoderov Mikhail
 */
@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    SectionRepository sectionRepository;

    @GetMapping
    public String mainPage(Model model) {
        model.addAttribute("title", "Home page :)");
        model.addAttribute("sections", sectionRepository.findAllByParent(null));

        return "main";
    }
}
