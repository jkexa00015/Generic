package com.jk.practice;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by exa00015 on 4/11/16.
 */
public class Dictionary {
    Map<String,String> wordmeaning;
    String word;
    String meaning;
    public Dictionary(String word,String meaning){
        this.word = word;
        this.meaning = meaning;
        wordmeaning = new HashMap<String, String>();
        wordmeaning.put(word,meaning) ;
    }
    public String getMeaning(String word){
        return wordmeaning.get(word);
    }

}
