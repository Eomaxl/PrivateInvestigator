package com.privateInvestigator.util;

import java.util.HashMap;
import java.util.Map;

public class WordCodeMapper {
    private Map<Integer, String> CodeMapper = new HashMap<>();
    private Map<String, Integer> WordMapper = new HashMap<>();

    public String getWord(Integer val){
        return CodeMapper.get(val);
    }

    public void addToMapper(String word, Integer val) {
        CodeMapper.put(val,word);
        WordMapper.put(word,val);
    }

    public Integer getWordBitValue(String str){
        Integer val = WordMapper.get(str);
        if(val == null) {
            addToMapper(str, val = 1 << WordMapper.size());
        }
        return val;
    }
}
