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
import java.util.Arrays;

public class Main {
    public static final double DELTA = 0.000001;
    public static void checkViterbi(String[] states, double[][] transitionMatrix, String[] observationVocab,
        double[][] emissionMatrix, String[] observations, ExpectedStateSequence expectedSequence) {
        ViterbiAlgorithm viterbi = new ViterbiAlgorithm(states, observationVocab, transitionMatrix, emissionMatrix);
        long time1 = System.currentTimeMillis();
        StateSequence sequence = viterbi.getStateSequence(observations);
        time1 = System.currentTimeMillis() - time1;
        System.out.println("Viterbi took " + time1 + "ms");
        // Check whether the result state sequence matches one of the expected
        // sequences
        int id = 0;
        while((id < expectedSequence.states.length) && (!Arrays.equals(sequence.states, expectedSequence.states[id]))) {
            ++id;
        }
        // If there is no expected squence that fits to the given result
        if(id >= expectedSequence.states.length) {
            StringBuilder message = new StringBuilder();
            message.append("The determined sequence ");
            message.append(Arrays.toString(sequence.states));
            message.append("\n does not match the expected state");
            if(expectedSequence.states.length > 1) {
                message.append("s ");
                for(int i = 0; i < expectedSequence.states.length; ++i) {
                    message.append('\n');
                    message.append(Arrays.toString(expectedSequence.states[i]));
                }
            } else {
                message.append(' ');
                message.append(Arrays.toString(expectedSequence.states[0]));
            }
            System.out.println(message.toString());
        }
        double diff = Math.abs(expectedSequence.logProbability - sequence.logProbability);
        if(diff > DELTA)
            System.out.println("The calculated probability (" + sequence.logProbability
                + ") does not match the expected probability (" + expectedSequence.logProbability + ").");
        System.out.println("Test passed");
    }
    public static void main(String[] args) {
        String observations[];
        ExpectedStateSequence expectedSequence;
        String[] states;
        String[] sequence;
        double[][] transitionMatrix;
        String[] observationVocab;
        double[][] emissionMatrix;
        System.out.println("---------- Ice cream example 1----------");
        states = new String[] { "HOT", "COLD" };
        transitionMatrix = new double[][] { { 0, 0.8, 0.2, 0 }, { 0, 0.6, 0.3, 0.1 }, { 0, 0.4, 0.5, 0.1 },
        { 0, 0, 0, 0 } };
        observationVocab = new String[] { "1", "2", "3" };
        emissionMatrix = new double[][] { { 0.2, 0.4, 0.4 }, { 0.5, 0.4, 0.1 } };
        observations = new String[] { "3", "1", "3" };
        expectedSequence = new ExpectedStateSequence(Math.log(0.0009216), new String[] { "HOT", "HOT", "HOT" });
        checkViterbi(states, transitionMatrix, observationVocab, emissionMatrix, observations, expectedSequence);

        observations = new String[] { "3", "2", "1", "1" };
        expectedSequence = new ExpectedStateSequence(Math.log(0.000288), new String[] { "HOT", "HOT", "COLD", "COLD" });
        checkViterbi(states, transitionMatrix, observationVocab, emissionMatrix, observations, expectedSequence);

        observations = new String[] { "1", "3", "3", "2", "3", "2", "1", "3", "1", "1", "1" };
        expectedSequence = new ExpectedStateSequence(Math.log(3.439853568E-9),
                new String[] { "HOT", "HOT", "HOT", "HOT", "HOT", "HOT", "HOT", "HOT", "COLD", "COLD", "COLD" });
        checkViterbi(states, transitionMatrix, observationVocab, emissionMatrix, observations, expectedSequence);
                System.out.println("---------- Ice cream example 2----------");
        observations = new String[1000];
        Arrays.fill(observations, "3");
        sequence = new String[1000];
        Arrays.fill(sequence, "HOT");
        expectedSequence = new ExpectedStateSequence(Math.log(0.8) + (999 * Math.log(0.6)) + (1000 * Math.log(0.4) + Math.log(0.1)), sequence);
        checkViterbi(states, transitionMatrix, observationVocab, emissionMatrix, observations, expectedSequence);
       
        System.out.println("----- EXTRA CASES -----");
        System.out.println("---------- Football manager example ----------");
        states = new String[] { "VICTORY", "DRAW", "DEFEAT" };
        transitionMatrix = new double[][] { { 0, 0.1, 0.5, 0.4, 0 }, { 0, 0.15, 0.4, 0.4, 0.05 }, { 0, 0.3, 0.3, 0.3, 0.1 }, { 0, 0.2, 0.2, 0.4, 0.2 }, { 0, 0, 0, 0, 0 } };
        observationVocab = new String[] { "CHAMPAGNE", "BEER", "COFFEE" };
        emissionMatrix = new double[][] { { 0.8, 0.15, 0.05 }, { 0.1, 0.7, 0.2 }, { 0.1, 0.4, 0.5 } };

        observations = new String[] { "COFFEE" };
        expectedSequence = new ExpectedStateSequence(Math.log(0.4) + Math.log(0.5) + Math.log(0.2), new String[] { "DEFEAT" });
        checkViterbi(states, transitionMatrix, observationVocab, emissionMatrix, observations, expectedSequence);

        observations = new String[] { "COFFEE", "COFFEE", "COFFEE" };
        expectedSequence = new ExpectedStateSequence( Math.log(0.4) + 3 * Math.log(0.5) + 2 * Math.log(0.4) + Math.log(0.2), new String[] { "DEFEAT", "DEFEAT", "DEFEAT" });
        checkViterbi(states, transitionMatrix, observationVocab, emissionMatrix, observations, expectedSequence);

        observations = new String[] { "CHAMPAGNE", "CHAMPAGNE", "CHAMPAGNE" };
        expectedSequence = new ExpectedStateSequence(Math.log(0.000096), new String[] { "DRAW", "VICTORY", "DEFEAT" });

    }
}
