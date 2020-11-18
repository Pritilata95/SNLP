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
public class main {
    public static void main(String[] args){
        String grammar1 = "State\ta\tb\t!\n0\t5\t1\t5\n1\t2\t5\t5\n2\t3\t5\t5\n3\t3\t5\t4\n4:\t5\t5\t5\n5\t5\t5\t5";
        Automata automata = new Automata(grammar1);
        System.out.println(automata.acceptsString("baca!"));
    }
}
