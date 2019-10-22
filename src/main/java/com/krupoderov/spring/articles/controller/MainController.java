package com.krupoderov.spring.articles.controller;

import com.krupoderov.spring.articles.repository.SectionRepository;
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

    private final SectionRepository sectionRepository;

    public MainController(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    @GetMapping
    public String mainPage(Model model) {
        model.addAttribute("title", "Home page :)");
        model.addAttribute("sections", sectionRepository.findAllByParent(null));

        return "main";
    }
}
