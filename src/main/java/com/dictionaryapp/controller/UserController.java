package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.UserLoginDTO;
import com.dictionaryapp.model.dto.UserRegisterDTO;
import com.dictionaryapp.service.UserService;
import com.dictionaryapp.util.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller

public class UserController {

    private UserService userService;

    private LoggedUser loggedUser;

    public UserController(UserService userService, LoggedUser loggedUser) {
        this.userService = userService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/register")
    public ModelAndView register() {
        if (loggedUser.isLogged()) {
            return new ModelAndView("redirect:/home");
        }

        return new ModelAndView("register");


    }

    @PostMapping("/register")
    public ModelAndView register(@Valid UserRegisterDTO userRegisterDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (loggedUser.isLogged()) {
            return new ModelAndView("redirect:/home");
        }

        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            bindingResult.addError(new FieldError(
                    "passwordNotMatched",
                    "confirmPassword",
                    "Password must be the same"));
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterDTO", userRegisterDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDTO", bindingResult);

            return new ModelAndView("redirect:/register");

        }
        userService.register(userRegisterDTO);

        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/login")
    public ModelAndView login() {
        if (loggedUser.isLogged()) {
            return new ModelAndView("redirect:/home");
        }


        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ModelAndView login(@Valid UserLoginDTO userLoginDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        if (loggedUser.isLogged()) {
            return new ModelAndView("redirect:/home");
        }

        if (bindingResult.hasErrors() || !userService.isLoginSuccessful(userLoginDTO)) {

            redirectAttributes.addFlashAttribute("userLoginDTO", userLoginDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginDTO", bindingResult);
            mv.setViewName("redirect:/login");

        }

        boolean validCredentials =userService.isLoginSuccessful(userLoginDTO) ;
        if(!validCredentials) {
            redirectAttributes.addFlashAttribute("loginDTO",userLoginDTO)
                    .addFlashAttribute("validCredentials",false);
            mv.setViewName("redirect:/login");
        }


        if (userService.isLoginSuccessful(userLoginDTO)) {
            userService.login(userLoginDTO);
            mv.setViewName("redirect:/home");
        }



        return mv;


    }

    @GetMapping("/logout")
    public ModelAndView logout(){

        if (!loggedUser.isLogged()){
            return  new ModelAndView("redirect:/login");
        }
        userService.logout();

        return  new ModelAndView("redirect:/");

    }

    @ModelAttribute
    public UserLoginDTO userLoginDTO() {
        return new UserLoginDTO();
    }

    @ModelAttribute
    public UserRegisterDTO userRegisterDto() {
        return new UserRegisterDTO();
    }
}
