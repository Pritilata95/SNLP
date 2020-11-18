/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FSA;

/**
 *
 * @author Pritilata
 */
public class Automata {
        // YOUR CODE HERE
    
    String[][] GrammerTable;
    int m;
    int n;

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
    }
    /**
     * This method should return true if the complete given text is accepted by the
     * FSA. If this is not the case, false should be returned.
     */
    public boolean acceptsString(String text) {
        boolean accepted = false;
        // YOUR CODE HERE
        //String text = "XYOJH";
        int index = 0;
        int current_state = 1;
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
                   new_state = GrammerTable[current_state][i];
                   current_state = Integer.parseInt(new_state) + 1;  
                   k = i;
                   if(current_state == m-1){
                       break;
                   }     
               } 
              }
           index++;
           if(alphabet[index-1].equals(GrammerTable[0][k])){
               continue;
           }
           else{
               break;
           }
           
        }
        String end_condition = String.valueOf(m-3) + ":";
        if (GrammerTable[current_state][0].equals(end_condition)){
            accepted = true;
        }
        
        return accepted;
    }
    
}
