package com.dictionaryapp.service.impl;

import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.enums.LanguageName;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.service.LanguageService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class LanguageServiceImpl implements LanguageService {
    private LanguageRepository languageRepository;

    public LanguageServiceImpl(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public void languageInit() {
        if (languageRepository.count() == 0) {
            Arrays.stream(LanguageName.values())
                    .forEach(l->{

                        Language language = new Language();
                        language.setLanguageName(l);
                        this.languageRepository.save(language);

                    });
        }
    }

    @Override
    public Language findByLanguageName(LanguageName language) {
            return this.languageRepository.findByLanguageName(language);
    }
}
