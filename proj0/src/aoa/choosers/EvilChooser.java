package aoa.choosers;

import java.util.*;

import edu.princeton.cs.algs4.StdRandom;
import aoa.utils.FileUtils;

public class EvilChooser implements Chooser {
    private String pattern;
    private List<String> wordPool;

    public EvilChooser(int wordLength, String dictionaryFile) {
        // TODO: Fill in this constructor.
        if(wordLength < 1) {
            throw new IllegalArgumentException("The wordLength is too short!");
        }
        List<String> words = FileUtils.readWordsOfLength(dictionaryFile, wordLength);
        int numWords = words.size();
        if(numWords == 0) {
            throw new IllegalStateException("There are no words found of wordLength!");
        }
        wordPool = words;
    }

    @Override
    public int makeGuess(char letter) {
        // TODO: Fill in this method.
        Map<StringBuilder, List<String>> map = new TreeMap<>();
        for(String elem: wordPool) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < elem.length(); i ++) {
                if(elem.charAt(i) == letter) {
                    sb.append(letter);
                } else if(pattern.charAt(i) != '-') {
                    sb.append(pattern.charAt(i));
                } else {
                    sb.append('-');
                }
            }

            if(map.containsKey(sb)) {
                List<String> wordsLst = map.get(sb);
                wordsLst.add(elem);
                map.put(sb, wordsLst);
            } else {
                List<String> wordsLst = new ArrayList<>();
                wordsLst.add(elem);
                map.put(sb, wordsLst);
            }
        }

        int maxNum = 0;
        for(StringBuilder sb: map.keySet()) {
            int num = map.get(sb).size();
            if(num > maxNum) {
                maxNum = num;
                wordPool = map.get(sb);
                pattern = sb.toString();
            }
        }

        int occurTime = 0;
        for(int i = 0; i < pattern.length(); i ++){
            if(pattern.charAt(i) == letter) {
                occurTime ++;
            }
        }
        return occurTime;
    }

    @Override
    public String getPattern() {
        // TODO: Fill in this method.
        if(pattern == null) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < wordPool.get(0).length(); i ++) {
                sb.append('-');
            }
            pattern = sb.toString();
        }
        return pattern;
    }

    @Override
    public String getWord() {
        // TODO: Fill in this method.
        return wordPool.get(0);
    }

}
