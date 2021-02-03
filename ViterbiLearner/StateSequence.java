/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViterbiLearner;

/**
 *
 * @author ASUS
 */
// YOUR CODE HERE

/**
 * Simple structure for storing a sequence of states and its probability.
 */
public class StateSequence {
    /**
     * The sequence of states.
     */
    public final String[] states;
    /**
     * The logarithm of the probability of this sequence.
     */
    public final double logProbability;

    public StateSequence(String[] states, double logProbability) {
        this.states = states;
        this.logProbability = logProbability;
    }
}
