package com.krupoderov.spring.articles.controller.forms;

import com.krupoderov.spring.articles.utils.Constants;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class RegistrationForm {

    @NotEmpty
    @Size(min = Constants.USERNAME_LENGTH_MIN, max = Constants.USERNAME_LENGTH_MAX)
    private String username;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(min = Constants.PASSWORD_LENGTH_MIN, max = Constants.PASSWORD_LENGTH_MAX)
    private String password;

    @NotEmpty
    @Size(min = Constants.PASSWORD_LENGTH_MIN, max = Constants.PASSWORD_LENGTH_MAX)
    private String confirmPassword;
}
