package com.example.ioc.service;

import com.example.ioc.service.impl.ISaveWord;

import java.util.List;

public class SaveService {
    private ISaveWord saveWord;

    public void setSaveWordImpl(List<String> words) {
        saveWord.save(words);
    }

    public ISaveWord getSaveWord() {
        return saveWord;
    }

    public void setSaveWord(ISaveWord saveWord) {
        this.saveWord = saveWord;
    }
}
