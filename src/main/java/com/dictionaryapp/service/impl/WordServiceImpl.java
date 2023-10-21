package com.dictionaryapp.service.impl;

import com.dictionaryapp.model.dto.AddWordDTO;
import com.dictionaryapp.model.dto.HomeDTO;
import com.dictionaryapp.model.dto.WordDTO;
import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.model.enums.LanguageName;
import com.dictionaryapp.repo.WordRepository;
import com.dictionaryapp.service.LanguageService;
import com.dictionaryapp.service.UserService;
import com.dictionaryapp.service.WordService;
import com.dictionaryapp.util.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WordServiceImpl implements WordService {
    private WordRepository wordRepository;
    private LanguageService languageService;
    private LoggedUser loggedUser;
    private UserService userService;

    public WordServiceImpl(WordRepository wordRepository, LanguageService languageService, LoggedUser loggedUser, UserService userService) {
        this.wordRepository = wordRepository;
        this.languageService = languageService;
        this.loggedUser = loggedUser;
        this.userService = userService;
    }

    @Override
    public void addProduct(AddWordDTO addWordDTO) {
        wordRepository.save(map(addWordDTO));
    }

    @Override
    public HomeDTO getHomeViewData() {

        HomeDTO homeDTO = new HomeDTO();

        Set<WordDTO> germanWords = this.getAllByCategoryName(LanguageName.GERMAN);
        Set<WordDTO> frenchWords = this.getAllByCategoryName(LanguageName.FRENCH);
        Set<WordDTO> spanishWords = this.getAllByCategoryName(LanguageName.SPANISH);
        Set<WordDTO> italianWords = this.getAllByCategoryName(LanguageName.ITALIAN);

        homeDTO.setGermanWords(germanWords);
        homeDTO.setFrenchWords(frenchWords);
        homeDTO.setSpanishWords(spanishWords);
        homeDTO.setItalianWords(italianWords);
        homeDTO.setGermanWordSize(germanWords.size());
        homeDTO.setFrenchWordSize(frenchWords.size());
        homeDTO.setSpanishWordSize(spanishWords.size());
        homeDTO.setItalianWordsSize(italianWords.size());

        return homeDTO;
    }

    @Override
    public void remove(Long id) {
        wordRepository.deleteById(id);
    }

    @Override
    public void removeAll() {
        this.wordRepository.deleteAll();
    }


    private Set<WordDTO> getAllByCategoryName(LanguageName languageName) {
        Language byLanguage = this.findByLanguage(languageName);
        return this.wordRepository.findAllByLanguage(byLanguage)
                .stream()
                .map(this::mapWordDTO)
                .collect(Collectors.toSet());
    }

    private Language findByLanguage(LanguageName languageName) {
        return this.languageService.findByLanguageName(languageName);
    }

    private WordDTO mapWordDTO(Word word) {

        WordDTO wordDTO = new WordDTO();

        wordDTO.setTerm(word.getTerm());
        wordDTO.setExample(word.getExample());
        wordDTO.setTranslation(word.getTranslation());
        wordDTO.setInputDate(word.getInputDate());
        wordDTO.setAddedBy(word.getAddedBy().getUsername());
        wordDTO.setId(word.getId());

        return wordDTO;

    }


    private Word map(AddWordDTO addWordDTO) {
        Word word = new Word();
        Language language = this.languageService.findByLanguageName(addWordDTO.getLanguage());

        language.setLanguageName(addWordDTO.getLanguage());

        User user = this.userService.findByUsername(loggedUser.getUsername());

word.setTerm(addWordDTO.getTerm());
word.setTranslation(addWordDTO.getTranslation());
word.setExample(addWordDTO.getExample());
word.setInputDate(addWordDTO.getInputDate());
word.setLanguage(language);
word.setAddedBy(user);


        return word;
    }
}
