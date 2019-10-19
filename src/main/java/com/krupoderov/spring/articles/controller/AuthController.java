package com.krupoderov.spring.articles.controller;

import com.krupoderov.spring.articles.controller.forms.RegistrationForm;
import com.krupoderov.spring.articles.controller.forms.UserValidatorForm;
import com.krupoderov.spring.articles.model.User;
import com.krupoderov.spring.articles.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    UserValidatorForm userValidatorForm;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(userValidatorForm);
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("title", "Registration");
        model.addAttribute("registrationForm", new RegistrationForm());
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String registration(
            @Valid RegistrationForm registrationForm,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "auth/registration";
        }

        User user = new User();
        user.setUsername(registrationForm.getUsername());
        user.setEmail(registrationForm.getEmail());
        user.setPassword(registrationForm.getPassword());
        user.setDateOfRegistration(new Date());

        userService.sighupUser(user);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model, String error) {
        model.addAttribute("title", "Sign In");

        if (error != null) {
            model.addAttribute("error", "Your username and password is invalid");
        }

        return "auth/login";
    }
}