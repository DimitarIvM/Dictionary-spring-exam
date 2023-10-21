package com.dictionaryapp.model.dto;

import com.dictionaryapp.validation.anotations.UniqueEmail;
import com.dictionaryapp.validation.anotations.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class UserRegisterDTO {

    @NotEmpty
    @Length(min = 3,max = 20,message = "Username length must be between 3 and 20 characters!")
    @UniqueUsername(message = "This username already exists!")
    private String username;
    @Email
    @NotEmpty(message = "Email cannot be empty!")
    @NotNull(message = "Email cannot be empty!")
    @UniqueEmail(message = "Email already exists!")
    private String email;
    @Length(min = 3,max = 20,message = "Password must be between 3 and 20 characters!")
    private String password;
    @Length(min = 3,max = 20,message = "Password must be between 3 and 20 characters!")
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
