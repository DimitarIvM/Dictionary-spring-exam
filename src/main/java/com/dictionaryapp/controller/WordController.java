package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.AddWordDTO;
import com.dictionaryapp.service.WordService;
import com.dictionaryapp.util.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/words")
public class WordController {

    private WordService wordService;
    private LoggedUser loggedUser;


    public WordController(WordService wordService, LoggedUser loggedUser) {
        this.wordService = wordService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/add")
    public ModelAndView addWords() {

        if (loggedUser.isLogged()){
            return new ModelAndView("word-add");
        }

return new ModelAndView("redirect:/");
    }

    @PostMapping("/add")
    public ModelAndView addWords(@Valid AddWordDTO addWordDTO,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {

        if (!loggedUser.isLogged()){
            return new ModelAndView("redirect:/");
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addWordDTO",addWordDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addWordDTO",bindingResult);

            return  new ModelAndView("redirect:/words/add");

        }

        wordService.addProduct(addWordDTO);
        return new ModelAndView("redirect:/home");
    }

    @PostMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id) {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        wordService.remove(id);

        return new ModelAndView("redirect:/home");
    }

    @PostMapping("/remove-all")
    public ModelAndView removeAll() {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        wordService.removeAll();

        return new ModelAndView("redirect:/home");
    }

    @ModelAttribute
    public AddWordDTO addWordDTO(){
        return new AddWordDTO();
    }
}
