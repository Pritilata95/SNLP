/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Viterbialgo;

/**
 *
 * @author ASUS
 */
/**
 * Simple structure representing a list of expected state sequences.
 * Note that in very rare cases more than one solution is possible.
 * This is why this class offers an array of state sequences.
 * It is not necessary to use this in the student's implementation!
 */
public class ExpectedStateSequence {
    /**
     * An array of expected states (and their alternatives).
     */
    public final String[][] states;
    /**
     * The logarithm of the probability of this sequence.
     */
    public final double logProbability;

    public ExpectedStateSequence(double logProbability, String[]...states) {
        this.states = states;
        this.logProbability = logProbability;
    }
}


