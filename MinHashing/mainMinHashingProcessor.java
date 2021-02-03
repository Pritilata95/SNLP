/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinHashing;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author ASUS
 */
public class mainMinHashingProcessor {
    public static void main(String[] args){
       /* MinHashingProcessor mp = new MinHashingProcessor(new int[][] {
        { 0, 9, 2, 7, 4, 5, 6, 3, 8, 1 }, 
        { 6, 8, 2, 5, 1, 7, 9, 4, 3, 0 },
        { 1, 8, 4, 6, 3, 9, 0, 5, 7, 2 },
        { 5, 0, 2, 7, 9, 4, 8, 1, 3, 6 }  });
        Set<Integer> s= new HashSet<>(Arrays.asList(0));*/
       // mp.minHash(s);
       MinHashingProcessor mp = new MinHashingProcessor(25,100,-999l);
        
    }
    
}
