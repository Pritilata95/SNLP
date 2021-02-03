/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NaiveBayesMultilevel;

import java.util.Set;

/**
 *
 * @author ASUS
 */
public class ClassifiedDocument {
    public final Set<String> classes;
    public final String text;
    public ClassifiedDocument(Set<String> classes, String text) {
        this.classes = classes;
        this.text = text;
    }
}
