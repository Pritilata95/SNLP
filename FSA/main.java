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
public class main {
    public static void main(String[] args){
        
        String grammar1 =  "State\t!\ti\ta\th\tI\n7\t7\t7\t7\t7\t7\n1\t7\t2\t4\t3\t7\n3\t7\t7\t4\t3\t7\n5:\t6\t7\t7\t5\t7\n6:\t7\t7\t7\t7\t7\n0\t7\t2\t7\t7\t1\n2\t7\t2\t4\t3\t7\n4:\t6\t7\t4\t5\t7";
        Automata automata = new Automata(grammar1);     
        System.out.println(automata.acceptsString("Iaaaaahhhh!a")) ;
        
   
    }
}
