package com.krupoderov.spring.articles.controller.forms;

import com.krupoderov.spring.articles.utils.Constants;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class ChangePasswordForm {

    @NotEmpty
    @Size(min = Constants.PASSWORD_LENGTH_MIN, max = Constants.PASSWORD_LENGTH_MAX)
    private String newPassword;

    @NotEmpty
    @Size(min = Constants.PASSWORD_LENGTH_MIN, max = Constants.PASSWORD_LENGTH_MAX)
    private String newConfirmPassword;
}
