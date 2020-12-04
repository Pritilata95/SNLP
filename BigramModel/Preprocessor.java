/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BigramModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List; 

/**
 *
 * @author Pritilata Saha
 */
public class Preprocessor {
    /**
     * The token used to indicate the beginning of a new sentence.
     */
    public static final String SENTENCE_START = "<s>";
    /**
     * The token used to indicate the end of a sentence.
     */
    public static final String SENTENCE_END = "</s>";

    /**
     * Preprocesses the given texts and returns the list of tokens.
     *
     * @param text the input text
     * @return the list of tokens extracted from the given text
     */
    public static List<String> preprocess(String text) {
        List<String> tokens = new ArrayList<String>();
        // YOUR CODE HERE
        String newtext = text.replaceAll("[^a-zA-Z0-9.!?]","\s");
        newtext = newtext.toLowerCase();
        String text_array[] = newtext.split("[.!?]");
        for(int i = 0; i<text_array.length; i++){
            if (text_array[i].isEmpty()) continue;
            else if (text_array[i].matches("\\s+")) continue;
            text_array[i]= text_array[i].replaceAll("^",SENTENCE_START + "\s");
            text_array[i]= text_array[i].replaceAll("$","\s"+ SENTENCE_END);
            tokens.addAll(Arrays.asList(text_array[i].split("\\s+")));
            
        }
        return tokens;
    }
    

    
}
