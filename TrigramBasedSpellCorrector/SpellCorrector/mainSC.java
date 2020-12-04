/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpellCorrector;

/**
 *
 * @author ASUS
 */
public class mainSC {
    public static void main(String[] args){
        String text1 ="London is the capital and largest city of England. Million people \" + \n" +
"    \"live in London. The River hames is in London. London is the largest city in \" +\n" +
"    \"Western Europe. The river Thmeos";
        TrigramBasedSpellCorrector sc = new TrigramBasedSpellCorrector(text1);
        //System.out.println(sc.getCorrection("live", "in", "lndon"));
        //System.out.println(sc.getCorrection("largest", "city", "on"));
        System.out.println(sc.getCorrection(" ", "   ", "          "));
        System.out.println(sc.getCorrection("largest", "city", " "));
        System.out.println(sc.getCorrection("natural", "language", "processing"));
    }
    
}
