package com.privateInvestigator.util;

import com.privateInvestigator.constants.Constants;

import java.util.ArrayList;
import java.util.List;

public class SentenceGrouping {
    WordCodeMapper wordCodeMapper = new WordCodeMapper();
    public List<String> filterSentences(List<String> sentences){
        List<Integer> individualSentenceValueList = new ArrayList<>();
        List<String> returnGroupedSentences = new ArrayList<>();

        for(String sentence: sentences){
            String[] words = sentence.split(" ");
            int sentenceValue = 0;
            for(int i = 2; i<words.length; i++){
                sentenceValue = sentenceValue | wordCodeMapper.getWordBitValue(words[i]);
            }
            individualSentenceValueList.add(sentenceValue);
        }
        for(int i =0; i<individualSentenceValueList.size(); i++){
            for(int j = i + 1; j<individualSentenceValueList.size(); j++){
                int xorValue = individualSentenceValueList.get(i) ^ individualSentenceValueList.get(j);
                int bitValue = bitCalculation(xorValue);
                if(bitValue == Constants.WORD_SHIFTING_POSITION) {
                    returnGroupedSentences.add(sentences.get(i));
                    returnGroupedSentences.add(sentences.get(j));
                    returnGroupedSentences.add("The changing word was: "+getMismatchedWords(xorValue));
                }
            }
        }
        return returnGroupedSentences;
    }

    private int bitCalculation(int num){
        int val = 0;
        while(num != 0) {
            val += num & 1;
            num = num >>> 1;
        }
        return val;
    }

    private String getMismatchedWords(int val){
        int x = val & -val;
        int y = val - x;
        return wordCodeMapper.getWord(x) + ", " + wordCodeMapper.getWord(y);
    }
}
