/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpellCorrector;

/**
 *
 * @author Pritilata Saha
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.*;
/**
 * A simple spell correction approach based on tri-grams and the Levenshtein
 * distance.
 */
public class TrigramBasedSpellCorrector {
    // YOUR CODE HERE
    LinkedHashSet<String> Trigram = new LinkedHashSet<String>();
    ArrayList<String> trigram;
    /**
     * The token used to indicate the beginning of a new sentence.
     */
    public static final String SENTENCE_START = "<s>";
    /**
     * The token used to indicate the end of a sentence.
     */
    public static final String SENTENCE_END = "</s>";
    /**
     * Constructor.
     */
    public TrigramBasedSpellCorrector(String text) {
        create(text);
    }

    /**
     * Internal methods for determining the necessary statistics about the tri-grams
     * of the given text.
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
    
    public int LevenshteinDistance(String string1, String string2) {
        int distance = 0;
        // YOUR CODE HERE
        int string1length =  string1.length();
        int string2length = string2.length();
        char[] String1 = string1.toCharArray();
        char[] String2= string2.toCharArray();
        int[][] Distance = new int[string1length+1][string2length+1];
        for(int i = 0; i <= string1length; i++){
            Distance[i][0] = i;
        }
        for(int i = 0; i <= string2length; i++){
            Distance[0][i] = i;
        }
        for(int i = 1; i <= string1length; i++){
            for(int j = 1; j <= string2length; j++){
                if(String1[i-1] == String2[j-1])
                    Distance[i][j] =  Math.min(Math.min(Distance[i][j - 1]+1, Distance[i - 1][j]+1),Distance[i - 1][j - 1] );          
                else{
                    Distance[i][j] = Math.min(Math.min(Distance[i][j - 1]+1, Distance[i - 1][j]+1),Distance[i - 1][j - 1]+1 );
                }
            }
        }
        distance = Distance[string1length][string2length];
    
        return distance;
    }
        
    protected void create(String text) {
        // YOUR CODE HERE
        List<String> Tokens = preprocess(text);
        List<String> TrigramTokens = new ArrayList<>();
        for(int i=0 ; i<(Tokens.size()-2);i++){
            TrigramTokens.add(Tokens.get(i) + " "+Tokens.get(i+1)+" "+Tokens.get(i+2));
        }
        Trigram.addAll(TrigramTokens);
        trigram = new ArrayList<String>(Trigram);
        System.out.println(trigram);
        
    }

    /**
     * Returns the correction of the third word based on the internal tri-grams that
     * start with word1 and word2 as well as the Levenshtein distance of candidates
     * from these tri-grams to the given word3.
     * 
     * @return a word for which a tri-gram with word1 and word2 at the beginning
     *         exists and which has the smallest Levenshtein distance to the given
     *         word3. Or null, if such a word does not exist.
     */
    public String getCorrection(String word1, String word2, String word3) {
        // YOUR CODE HERE
        String corrected_word=null;
        String last_word=null;
        String previousWords = new String();
        int maxdistance = 999999999;
        if(word1==""||word2==""||word3=="")previousWords=null;
        else
        previousWords = (word1.replaceAll("[^a-zA-Z0-9]", " ")+" "+word2.replaceAll("[^a-zA-Z0-9]", " ")).toLowerCase();
        
        //System.out.println(previousWords);
        word3 = word3.replaceAll("[^a-zA-Z0-9]", " ");

        try{
            String[] PreviousWords = previousWords.split(" ");
            for(int i=0; i<trigram.size();i++){
            String[] currenttrigram = (trigram.get(i)).split(" ");
            //System.out.println(PreviousWords[0]);
            //System.out.println(currenttrigram[0]);
            if(previousWords==null){
                corrected_word=null;
            }
            else if(currenttrigram[0].equals(PreviousWords[0])&&currenttrigram[1].equals(PreviousWords[1])){
               last_word = currenttrigram[2];
               //System.out.println("here");
               int distance = LevenshteinDistance(last_word, word3);
               if(distance<maxdistance){
                   corrected_word=last_word;
                   maxdistance=distance;
                   
               } 
                
            }
        }
        }
        catch(Exception e)  
        {  
           corrected_word = null;   
        }  
        
        
        return corrected_word; 
    }
    
}

// This line should make sure that compile errors are directly identified when executing this cell
// (the line itself does not produce any meaningful result)
//Arrays.sort(new Object[]{new TrigramBasedSpellCorrector("")});
