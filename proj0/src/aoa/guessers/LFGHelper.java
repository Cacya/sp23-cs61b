package aoa.guessers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LFGHelper {
    /** Return the maxFrequency character */
    public static char getMaxFrequencyChar(List<String> newWords, List<Character> guesses){
        char guess = '?';
        Map<Character, Integer> fmap = LFGHelper.getFrequencyMap(newWords);
        int max_frequence = 0;
        for(Character key: fmap.keySet()) {
            if(!guesses.contains(key) && fmap.get(key) > max_frequence) {
                max_frequence = fmap.get(key);
                guess = key;
            }
        }
        return guess;
    }

    /** Return the frequency map */
    public static Map<Character, Integer> getFrequencyMap(List<String> newWords) {
        Map<Character, Integer> map = new TreeMap<>();
        for(String elem: newWords) {
            for(int i = 0; i < elem.length(); i ++) {
                char c = elem.charAt(i);
                if(map.containsKey(c)) {
                    map.put(c, map.get(c) + 1);
                } else {
                    map.put(c, 1);
                }
            }
        }
        return map;
    }

    /** Returns the map of pattern */
    public static Map<Integer, Character> getPatternMap(String pattern) {
        Map<Integer, Character> patternMap = new HashMap<>();
        for(int i = 0; i < pattern.length(); i ++) {
            if (pattern.charAt(i) != '-') {
                patternMap.put(i, pattern.charAt(i));
            }
        }
        return patternMap;
    }
}
