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
/*

We are using the baa! sheep example from the slides but your implementation 
has to accept other grammars as well. The transition matrix is given as a tab separated String:

State    a    b    !
0    5    1    5
1    2    5    5
2    3    5    5
3    3    5    4
4:    5    5    5
5    5    5    5

(Note that this notebook renders the example above with whitespaces but the data in the tests will use tabs.) 
For the line endings, the \n character is used (might be important for Windows users!).

The transition matrix contains a head line and after that one state per line.
The head line starts with the String State. After that, the single input characters are listed.
The single status lines show the transitions from the left state to the other states 
given the input characters defined in the head state. 
For example, there is a transition from state 0 to 5 if a or ! are read and a transition to 1 if b is read.
The end state(s) are marked with :. In the example, state 4 is the only end state.

Hints
* 0 is always the start state.
* There can be multiple end states.
* The state with the highest ID in the grammar is always the error state (in the example, it is the state 5). 
  This is the state the automata gets into if a character is read that does not fit 
  to the grammar at that particular position. It can also be seen that the automata can not leave the error state.
* All characters that are not listed in the head should directly lead to the error state.
* The lines below the head line don't have to be ordered (i.e., the states can be defined in any order).
* The class Automata has two methods - parseGrammar and acceptsString. 
  The first method is called when constructing the Automata and should parse the grammar. 
  Later, the second method is called and should calculate a result based on the grammar and the input String. 
  Note that you may want to add some class attributes to make sure that you can store the parsed information 
  (i.e., the transitions) in the parseGrammar method to be able to use them later on in the acceptsString method.

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
