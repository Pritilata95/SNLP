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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.util.Arrays;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

public class Main {
    public static void checkHMMLearningViaViterbi(String filename, int numberOfStates, int sizeOfVocab,
            String[][] testObservations, String[][] expectedSequences) throws Throwable {
        LineIterator iterator = null;
        iterator = FileUtils.lineIterator(new File(filename), "utf-8");
        long time1 = System.currentTimeMillis();
        HMMLearner learner = new HMMLearner(numberOfStates, sizeOfVocab);
        while (iterator.hasNext()) {
            learner.processSequence(iterator.next());
        }
        ViterbiAlgorithm viterbi = learner.buildViterbi();
        time1 = System.currentTimeMillis() - time1;
        System.out.println("Learning took " + time1 + "ms");
        // Test
        time1 = System.currentTimeMillis();
        for (int i = 0; i < expectedSequences.length; i++) {
            StateSequence sequence = viterbi.getStateSequence(testObservations[i]);
            if(!Arrays.deepEquals(sequence.states, expectedSequences[i])) {
                System.out.println("The calculated sequence " + Arrays.toString(sequence.states)
                        + " does not match the expected sequence " + Arrays.toString(expectedSequences[i]));
                continue;
            }
            System.out.println("Test " + i + " passed");
        }
        time1 = System.currentTimeMillis() - time1;
        System.out.println("Testing took " + time1 + "ms");
        if (iterator != null) {
            iterator.close();
        }
        learner.myprint();
    }
   
    public static void main(String[] args) throws Throwable {
////////        HMMLearner HMML=new HMMLearner(2,3);
////////        HMML.processSequence("2_HOT 2_COLD 3_COLD 1_COLD 3_COLD 1_COLD 2_COLD 3_COLD 3_HOT 3_HOT 3_HOT 3_HOT 2_HOT 2_COLD");
////////        HMML.processSequence("3_HOT 2_HOT 3_HOT 2_HOT 2_COLD");
////////        HMML.processSequence("3_HOT 3_COLD 1_COLD 2_HOT 2_HOT 2_COLD 3_HOT 2_HOT");
////////        HMML.processSequence("1_COLD 1_COLD 3_HOT 1_HOT");
////////        HMML.buildViterbi();       
////////        HMML.myprint();
        String[][] testObservations;
        String[][] expectedSequences;

        System.out.println("---------- Ice cream example ----------");
        testObservations = new String[][] { { "3", "1", "3" }, { "3", "2", "1", "1" },
            { "1", "3", "3", "2", "3", "2", "1", "3", "1", "1", "1" }, new String[1000] };
        expectedSequences = new String[][] { { "HOT", "HOT", "HOT" }, { "HOT", "HOT", "COLD", "COLD" },
            { "HOT", "HOT", "HOT", "HOT", "HOT", "HOT", "HOT", "HOT", "COLD", "COLD", "COLD" }, new String[1000] };
        Arrays.fill(testObservations[3], "3");
        Arrays.fill(expectedSequences[3], "HOT");
        checkHMMLearningViaViterbi("./icecream-sequences.txt", 2, 3, testObservations, expectedSequences);
    }
}
