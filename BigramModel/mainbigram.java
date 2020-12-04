/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BigramModel;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Pritilata Saha
 */
public class mainbigram {
            public static void main(String[] args){
            //tring text1 =  "London is the capital and largest city of England. Million people live in London. The river Thames is in London. London is the largest city in Western-Europe.";
//"London is the capital and largest city of England. Million people live in London. The river Thames is in London. London is the largest city in Western-Europe.";
//"She said:\"I know that she likes English food!\"";
            String text1="Worth every penny. Seriously it's same as shown in the pic. It's so light weight, even after wearing for an hour , it doesn't hurt. No doubt it's beautiful and perfect for festive occasions. Thanks ZAVERI PEARLS and FLIPKART for such an amazing earring set. amazing..more than enough to rock the wedding party in ethnic look. size is large compare to looking in the pic. But it's beautiful. Something for I was really waiting to add on my earring collection.";
            Preprocessor.preprocess("").clear();
            System.out.println(Preprocessor.preprocess(text1)) ;
            BigramMatrix bm = new BigramMatrix(Preprocessor.preprocess(text1));
            //List<String> tokens = Arrays.asList("<s>", "she", "said", "i", "know", "that", "she", "likes",
            //                           "english", "food", "</s>");
            //BigramMatrix bm = new BigramMatrix(tokens);
            bm.normalize();
            bm.performLaplaceSmoothing();
            //System.out.println(bm.getNormalizedCount("worth", "every")+","+bm.getNormalizedCount("light", "weight")+","+bm.getNormalizedCount("beautiful", "and"));
            //System.out.println(bm.getCount("worth", "every")+","+bm.getCount("light", "weight")+","+bm.getCount("beautiful", "and"));
            //System.out.println(bm.getCount("she", "said")+","+bm.getCount("english", "food")+","+bm.getCount("likes", "food"));
            //System.out.println(bm.getCount("london", "</s>")+","+bm.getCount("largest", "city")+","+bm.getCount("river", "thames")+","+bm.getCount("city", "river"));
            //System.out.println(bm.getNormalizedCount("london", "underground")+","+bm.getNormalizedCount("small", "city")+","+bm.getNormalizedCount("sky", "scraper"));
            //System.out.println(bm.getCount("london", "underground")+","+bm.getCount("small", "city")+","+bm.getCount("sky", "scraper"));
 
            }
    
}
