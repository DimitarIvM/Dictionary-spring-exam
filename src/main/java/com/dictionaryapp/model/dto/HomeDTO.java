package com.dictionaryapp.model.dto;

import java.util.Set;

public class HomeDTO {

    private Set<WordDTO> germanWords;
    private Set<WordDTO> frenchWords;
    private Set<WordDTO> spanishWords;
    private Set<WordDTO> italianWords;

    private int germanWordSize;
    private int frenchWordSize;
    private int spanishWordSize;
    private int italianWordsSize;






    public int getGermanWordSize() {
        return germanWordSize;
    }

    public void setGermanWordSize(int germanWordSize) {
        this.germanWordSize = germanWordSize;
    }

    public int getFrenchWordSize() {
        return frenchWordSize;
    }

    public void setFrenchWordSize(int frenchWordSize) {
        this.frenchWordSize = frenchWordSize;
    }

    public int getSpanishWordSize() {
        return spanishWordSize;
    }

    public void setSpanishWordSize(int spanishWordSize) {
        this.spanishWordSize = spanishWordSize;
    }

    public int getItalianWordsSize() {
        return italianWordsSize;
    }

    public void setItalianWordsSize(int italianWordsSize) {
        this.italianWordsSize = italianWordsSize;
    }

    public Set<WordDTO> getGermanWords() {
        return germanWords;
    }

    public void setGermanWords(Set<WordDTO> germanWords) {
        this.germanWords = germanWords;
    }

    public Set<WordDTO> getFrenchWords() {
        return frenchWords;
    }

    public void setFrenchWords(Set<WordDTO> frenchWords) {
        this.frenchWords = frenchWords;
    }

    public Set<WordDTO> getSpanishWords() {
        return spanishWords;
    }

    public void setSpanishWords(Set<WordDTO> spanishWords) {
        this.spanishWords = spanishWords;
    }

    public Set<WordDTO> getItalianWords() {
        return italianWords;
    }

    public void setItalianWords(Set<WordDTO> italianWords) {
        this.italianWords = italianWords;
    }
}
