package com.dictionaryapp.validation;


import com.dictionaryapp.service.UserService;
import com.dictionaryapp.validation.anotations.UniqueUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    UserService userService;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {

        return !userService.existsByUsername(username);

    }
}
