package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.HomeDTO;
import com.dictionaryapp.model.dto.WordDTO;
import com.dictionaryapp.service.WordService;
import com.dictionaryapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@Controller
public class HomeController {
    private LoggedUser loggedUser;
    private WordService wordService;

    public HomeController(LoggedUser loggedUser, WordService wordService) {
        this.loggedUser = loggedUser;
        this.wordService = wordService;
    }

    @GetMapping("/")
    public ModelAndView index() {
        if (loggedUser.isLogged()) {
            return new ModelAndView("redirect:/home");
        }


        return new ModelAndView("index");
    }

    @GetMapping("/home")
    public ModelAndView home() {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        ModelAndView modelAndView = new ModelAndView();

        Set<WordDTO> germanWords =  this.wordService.getHomeViewData().getGermanWords();
        Set<WordDTO> frenchWords =  this.wordService.getHomeViewData().getFrenchWords();
        Set<WordDTO> spanishWords =  this.wordService.getHomeViewData().getSpanishWords();

        Set<WordDTO> italianWords =  this.wordService.getHomeViewData().getItalianWords();
       int allWords = germanWords.size() + frenchWords.size() + spanishWords.size() + italianWords.size();


        modelAndView.setViewName("home");

        modelAndView.addObject("germanWords",germanWords);
        modelAndView.addObject("frenchWords",frenchWords);
        modelAndView.addObject("spanishWords",spanishWords);
        modelAndView.addObject("italianWords",italianWords);
        modelAndView.addObject("allWordsCount",allWords);


        return modelAndView;
    }
}
