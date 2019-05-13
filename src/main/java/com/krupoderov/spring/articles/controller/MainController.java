package com.krupoderov.spring.articles.controller;

import com.krupoderov.spring.articles.model.Article;
import com.krupoderov.spring.articles.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/blog") // адрес, по которому будет вызван контроллер
public class MainController {

    @Autowired
    ArticleService service;

    @RequestMapping
    public String mainPage(Model model) {
        model.addAttribute("articles", service.getAll());
        return "main";
    }

    @RequestMapping(value = "/editor")
    public String editorPage(Model model) {
        model.addAttribute("article", new Article());
        return "editor";
    }

    @RequestMapping(value = "/editor/submit", method = RequestMethod.POST)
    public String submitArticle(@ModelAttribute Article article) {
        service.save(article);
        return "redirect:../";
    }

    @RequestMapping(value = "/editor/delete/{article_id}")
    public String deleteArticle(@PathVariable("article_id") Integer articleId) {
        service.delete(articleId);
        return "redirect:../";
    }

    @RequestMapping(value = "/login")
    public String loginPage() {
        return "login";
    }
}
