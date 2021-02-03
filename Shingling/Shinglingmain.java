/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shingling;

/**
 *
 * @author  Pritilata Saha
 */
public class Shinglingmain {
    public static void main(String[]args){
        String text = "abcd";
        String text1 = "abcd";
        ShinglingProcessor sp = new ShinglingProcessor(5);
        sp.jaccardSim(sp.applyShingling(text1),sp.applyShingling(text));
    }

}
