/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shingling;

/**
 *
 * @author Pritilata Saha
 */
import java.util.*;
public class ShinglingProcessor {
        // YOUR CODE HERE
        ArrayList<String> Shingles = new ArrayList<String>();
        int length=0;
    public ShinglingProcessor(int shingleLength) {
        // YOUR CODE HERE
        this.length = shingleLength;
    }

    public Set<Integer> applyShingling(String text) {
        Set<Integer> shingles=new LinkedHashSet<Integer>();
        // YOUR CODE HERE     
        String token = "";
        for(int i=0;i<=(text.length()-length);i++){
            token=text.substring(i, i+(length));
            if(!Shingles.contains(token)) {
                Shingles.add(token); 
            }
            shingles.add(Shingles.indexOf(token));
        }
         System.out.println(Shingles);
         System.out.println(shingles);
        return shingles;
    }
    
    public static double jaccardSim(Set<Integer> set1, Set<Integer> set2) {
        double similarity = 0;
        // YOUR CODE HERE
        Set<Integer> set = new LinkedHashSet<Integer>();
        set.addAll(set1);
        set.addAll(set2);
        set1.retainAll(set2);
        System.out.println(set1);
        System.out.println(set);
        similarity=Double.valueOf(set1.size())/Double.valueOf(set.size());
        System.out.println(similarity);        
        return similarity;
    }
    
}
