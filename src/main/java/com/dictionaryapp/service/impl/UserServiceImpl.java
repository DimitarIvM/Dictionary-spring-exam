package com.dictionaryapp.service.impl;

import com.dictionaryapp.model.dto.UserLoginDTO;
import com.dictionaryapp.model.dto.UserRegisterDTO;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.service.UserService;
import com.dictionaryapp.util.LoggedUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    private LoggedUser loggedUser;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

        this.loggedUser = loggedUser;
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    @Override
    public void register(UserRegisterDTO userRegisterDto) {
        userRepository.save(map(userRegisterDto));
    }

    @Override
    public void login(UserLoginDTO userLoginDTO) {
        User user = findByUsername(userLoginDTO.getUsername());

        if (user != null) {

            if (isLoginSuccessful(userLoginDTO)) {

                loggedUser.setLogged(true);
                loggedUser.setId(user.getId());
                loggedUser.setUsername(userLoginDTO.getUsername());

            }

        }
    }

    public boolean isLoginSuccessful(UserLoginDTO userLoginDTO) {
        User user = this.findByUsername(userLoginDTO.getUsername());
        if (user == null) {
            return false;

        }
        String encodedPassword = user.getPassword();

        String rawPassword = userLoginDTO.getPassword();

        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }


    private User map(UserRegisterDTO userRegisterDto) {

        User user = new User();

        user.setUsername(userRegisterDto.getUsername());
        user.setPassword(passwordEncoder.encode((userRegisterDto.getPassword())));
        user.setEmail(userRegisterDto.getEmail());


        return user;
    }

    public void logout() {
        this.loggedUser.setLogged(false);
        this.loggedUser.setUsername(null);
        this.loggedUser.setId(null);
    }



}
