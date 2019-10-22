package com.krupoderov.spring.articles.controller.forms;

import com.krupoderov.spring.articles.model.User;
import com.krupoderov.spring.articles.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
public class UserValidatorForm implements Validator {

    private final UserRepository userRepository;

    public UserValidatorForm(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return RegistrationForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RegistrationForm registrationForm = (RegistrationForm) o;

        if (!registrationForm.getPassword().equals(registrationForm.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "registration.error.passwordsNotMatch");
        }

        User savedUserByName = userRepository.findByUsername(registrationForm.getUsername());
        User savedUserByEmail = userRepository.findByEmail(registrationForm.getEmail());

        if (Objects.nonNull(savedUserByName)) {
            errors.rejectValue("username", "registration.error.username.nonUnique");
        }

        if (Objects.nonNull(savedUserByEmail)) {
            errors.rejectValue("email", "registration.error.email.nonUnique");
        }
    }
}
