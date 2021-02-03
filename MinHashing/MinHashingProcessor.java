/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinHashing;

import java.util.*;

/**
 *
 * @author Pritilata Saha
 */

public class MinHashingProcessor {
    // YOUR CODE HERE
    int[][] Permutations;
    /**
     * Constructor for creating the class with an already given set of permutations.
     */
    public MinHashingProcessor(int[][] permutations) {
        // YOUR CODE HERE
        this.Permutations=permutations;
    }

    /**
     * Constructor for creating the class with a generated set of permutations.
     * 
     * @param numberOfHashes
     *            number of hash functions (i.e., different permutations) that
     *            should be generated
     * @param numberOfShingles
     *            number of different shingles the given documents can have
     * @param seed
     *            a seed if the generation is based on a random process
     */
    public MinHashingProcessor(int numberOfHashes, int numberOfShingles, long seed) {
        // YOUR CODE HERE
            Permutations= new int[numberOfHashes][numberOfShingles];
            List<Integer> randomNumbers = new ArrayList<Integer>();
            Random r = new Random(seed);
            for(int i =0;i<numberOfHashes;i++){
                for(int j =0;j<numberOfShingles;j++){     
                    int a=r.nextInt(numberOfShingles);
                    while(randomNumbers.contains(a))a=r.nextInt(numberOfShingles);
                    Permutations[i][j]=a; 
                    randomNumbers.add(a);
                } 
                randomNumbers = new ArrayList<Integer>();
            }

            for(int i =0;i<numberOfHashes;i++){
                for(int j =0;j<numberOfShingles;j++){ 
            System.out.print(Permutations[i][j]+"\s");}
                System.out.println();
            }
    }

    public int[] minHash(Set<Integer> s) {
        int hash[] = new int[Permutations.length]; 
        // YOUR CODE HERE
        for(int i = 0; i<Permutations.length;i++){
            for(int j = 0; j<Permutations[0].length;j++){
                if(s.contains(Permutations[i][j])){
                    hash[i]=j;
                    break;
                }
            }
        }
        //for(int i = 0; i<hash.length;i++)
        //System.out.print(hash[i]+"\s");
        return hash;
    }
}
