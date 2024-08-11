package aoa.choosers;

import edu.princeton.cs.algs4.StdRandom;
import aoa.utils.FileUtils;

import java.util.List;

public class RandomChooser implements Chooser {
    private final String chosenWord;
    private String pattern;

    public RandomChooser(int wordLength, String dictionaryFile) {
        // TODO: Fill in/change this constructor.
        //You should throw an IllegalArgumentException if wordLength is less than one.
        // You should throw an IllegalStateException if there are no words found of wordLength.
        if(wordLength < 1) {
            throw new IllegalArgumentException("The wordLength is too short!");
        }
        List<String> words = FileUtils.readWordsOfLength(dictionaryFile, wordLength);
        int numWords = words.size();
        if(numWords == 0) {
            throw new IllegalStateException("There are no words found of wordLength!");
        }
        int randomlyChosenWordNumber = StdRandom.uniform(numWords);
        chosenWord = words.get(randomlyChosenWordNumber);
    }

    @Override
    public int makeGuess(char letter) {
        // TODO: Fill in this method.
        int cnt = 0;
        StringBuilder sb = new StringBuilder(pattern);
        if(chosenWord == null) return 0;
        for(int i = 0; i < chosenWord.length(); i ++) {
            if (chosenWord.charAt(i) == letter) {
                cnt ++;
                sb.setCharAt(i, letter);
            }
        }
        pattern = sb.toString();
        return cnt;
    }

    @Override
    public String getPattern() {
        // TODO: Fill in this method.
        if(pattern == null) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < chosenWord.length(); i ++) {
                sb.append('-');
            }
            pattern = sb.toString();
        }
        return pattern;
    }

    @Override
    public String getWord() {
        // TODO: Fill in this method.
        return chosenWord;
    }
}
