package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.AddWordDTO;
import com.dictionaryapp.model.dto.HomeDTO;

public interface WordService {
    void addProduct(AddWordDTO addWordDTO);

    HomeDTO getHomeViewData();

    void remove(Long id);

    void removeAll();
}
