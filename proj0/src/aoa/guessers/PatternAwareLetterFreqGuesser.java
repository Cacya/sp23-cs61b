package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.*;

public class PatternAwareLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PatternAwareLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN. */
    public char getGuess(String pattern, List<Character> guesses) {
        // TODO: Fill in this method.
        List<String> newWords = getNewWords(pattern);
        return LFGHelper.getMaxFrequencyChar(newWords, guesses);
    }

    /** Return the new list of words */
    public List<String> getNewWords(String pattern) {
        Map<Integer, Character> patternMap = LFGHelper.getPatternMap(pattern);
        List<String> newWords = new ArrayList<>();
        for(String elem: words) {
            if(pattern.length() != elem.length()){
                continue;
            }
            boolean flag = true;
            for(Integer i: patternMap.keySet()){
                if(elem.charAt(i) != patternMap.get(i)) {
                    flag = false;
                }
            }
            if(flag){
                newWords.add(elem);
            }
        }
        return newWords;
    }

    public static void main(String[] args) {
        PatternAwareLetterFreqGuesser palfg = new PatternAwareLetterFreqGuesser("data/example.txt");
        System.out.println(palfg.getGuess("-e--", List.of('e')));
    }
}