package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.UserLoginDTO;
import com.dictionaryapp.model.dto.UserRegisterDTO;
import com.dictionaryapp.model.entity.User;

public interface UserService {
    boolean existsByUsername(String username);

    boolean existsUserByEmail(String email);

    void register(UserRegisterDTO userRegisterDto);

    void login(UserLoginDTO userLoginDTO);

    void logout();

    boolean isLoginSuccessful(UserLoginDTO userLoginDTO);

    User findByUsername(String username);
}
