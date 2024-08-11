package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.*;

public class PAGALetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PAGALetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN and the GUESSES that have been made. */
    public char getGuess(String pattern, List<Character> guesses) {
        // TODO: Fill in this method.
        List<String> newWords = getNewWords(pattern, guesses);
        return LFGHelper.getMaxFrequencyChar(newWords, guesses);
    }

    /** Return the new words list */
    public List<String> getNewWords(String pattern, List<Character> guesses) {
        Map<Integer, Character> patternMap = LFGHelper.getPatternMap(pattern);
        List<String> newWords = new ArrayList<>();
        //遍历elem
        //在其他地方包含guess，则去掉
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
            for(int i = 0; i < elem.length(); i ++) {
                if(!patternMap.containsKey(i) && guesses.contains(elem.charAt(i))) {
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
        PAGALetterFreqGuesser pagalfg = new PAGALetterFreqGuesser("data/example.txt");
        System.out.println(pagalfg.getGuess("----", List.of('e')));
    }
}
