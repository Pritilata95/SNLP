/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FSA;

import java.util.Arrays;

/**
 *
 * @author Pritilata
 */
public class Automata {
        // YOUR CODE HERE
    
    String[][] GrammerTable;
    int m;
    int n;
    String[] end_state;

    /**
     * Constructor taking the grammar String which defines the behavior of this
     * automata.
     */
    public Automata(String grammarDef) {
        parseGrammar(grammarDef);
    }

    /**
     * An internal method that parses the given grammar String.
     */
    protected void parseGrammar(String grammarDef) {
        // YOUR CODE HERE
        String rowno[] = grammarDef.split("\\n");
        String columnno[] = rowno[0].split("\\t");
        m = rowno.length;
        n = columnno.length;
        GrammerTable = new String[m][n];
        for(int i=0;i<m;i++){
            String row[] = rowno[i].split("\\t");
            for(int j=0;j<n;j++){
                GrammerTable[i][j]= row[j];
            }
        }  
        String end_state_string = "";
        for(int x = 1; x<m; x++){
            String chars = String.valueOf(x-1) + ":";
            for(int y = 1; y<m; y++){
               if(GrammerTable[y][0].equals(chars)){
                   end_state_string = end_state_string + GrammerTable[y][0] + "\t";
               }
            }
        }
        end_state = end_state_string.split("\t"); 
    }
    /**
     * This method should return true if the complete given text is accepted by the
     * FSA. If this is not the case, false should be returned.
     */
    public boolean acceptsString(String text) {
        boolean accepted = false;
        // YOUR CODE HERE
        int index = 0;
        int current_state = 0;
        int current_state_index=0;
            for(int i = 1; i < m; i++){
            String nchar = String.valueOf(current_state);
            if(GrammerTable[i][0].equals(nchar)){
                current_state_index = i;
            }
        }
        String new_state;
        int l = text.length();
        String[] alphabet = new String[l];
        for (int i = 0; i < l; i++) { 
              alphabet[i] = String.valueOf(text.charAt(i)); // making a string array alphabet from text
        } 
        int k = 0;
        while(index<l){
           for(int i = 1; i<n; i++){
               if(alphabet[index].equals(GrammerTable[0][i])){ // finding the alphabet in GrammerTable header
                   new_state = GrammerTable[current_state_index][i];
                   for(int indexn = 1; indexn < m; indexn++){
                        if(GrammerTable[indexn][0].equals(new_state)){
                        current_state_index = indexn;
                        }
                        else if(GrammerTable[indexn][0].equals(new_state+":")){
                            current_state_index = indexn;
                        }
                    }            
                   k = i;
                   if(current_state_index == m-1){
                       break;
                   }     
               } 
            }
           index++;
           if(!alphabet[index-1].equals(GrammerTable[0][k])){
               break;
           }
           
        }
        
        
        if (Arrays.binarySearch(end_state,GrammerTable[current_state_index][0])>= 0){
            accepted = true;
        }
        
        return accepted;
    }
    
}
