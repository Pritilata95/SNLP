 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tokenizer;


/**
 *
 * @author Pritilata Saha
 */
public class maintokenizer {
        public static void main(String[] args){
            String text1 = "She said:\"I know that she likes English food!\"^^@@@#fucking_genius~~^"; 
            Preprocessor.preprocess("").clear();
            System.out.println(Preprocessor.preprocess(text1)) ;
            text1 = "I don't eat this soup. No, I will not eat this soup. Sou-p is not what I like to eat 24/7.!!!!!!! `~!@#$%^&*()_-+=!!!!";
            Preprocessor.preprocess("").clear();
            System.out.println(Preprocessor.preprocess(text1)) ;
   
    }
    
}
