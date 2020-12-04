/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LevenshteinDistance;

/**
 *
 * @author Pritilata Saha
 */
public class LevenshteinDistance {
        
    public int calculate(String string1, String string2) {
        int distance = 0;
        // YOUR CODE HERE
        int string1length =  string1.length();
        int string2length = string2.length();
        char[] String1 = string1.toCharArray();
        char[] String2= string2.toCharArray();
        int[][] Distance = new int[string1length+1][string2length+1];
        for(int i = 1; i <= string1length; i++){
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
        /*for(int i = 0; i <= string1length; i++){
            for(int j = 0; j <= string2length; j++){
                System.out.print (Distance[i][j]+" ");
            }
            System.out.println();
        }*/
    
        return distance;
    }
}

// This line should make sure that compile errors are directly identified when executing this cell
// (the line itself does not produce any meaningful result)
//Arrays.sort(new int[(new LevenshteinDistance()).calculate("a","a")]);
    
