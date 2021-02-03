/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NaiveBayesClassifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Pritilata Saha
 */
public class main {
    public static void main(String[] args) {
        System.out.println("---------- Simple example corpus ----------");
        List<String[]> exampleCorpusTrain = Arrays.asList(
        new String[] {"chess", "white king, black rook, black queen, white pawn, black knight, white bishop." },
        new String[] {"history", "knight person granted honorary title knighthood" },
        new String[] {"history", "knight order eligibility, knighthood, head of state, king, prelate, middle ages." },
        new String[] {"chess", "Defense knight pawn opening game opponent." },
        new String[] {"literature", "Knights Round Table. King Arthur. literary cycle Matter of Britain."}
        );        
        List<String[]> exampleCorpusTest = Arrays.asList(
        new String[] {"history", "Knighthood Middle Ages." },
        new String[] {"chess", "player king knight opponent king checkmate game draw." },
        // document with unknown words
        new String[] {"literature", "britain king arthur. Sir Galahad." }
        );

        // Determine the classes
        Set<String> classes = Arrays.asList(exampleCorpusTrain, exampleCorpusTest).stream().flatMap(l -> l.stream()).map(d -> d[0]).distinct().collect(Collectors.toSet());
        //System.out.println(classes);
       
        // Determine the number of instances per class in the evaluation set
        Map<String, Long> evalClassCounts = exampleCorpusTest.stream().collect(Collectors.groupingBy(d -> d[0], Collectors.counting()));
        for (String clazz : classes) {
            if (!evalClassCounts.containsKey(clazz)) {
                evalClassCounts.put(clazz, 0L);
            }
        }

        // Determine the expected accuracies of the baselines
        Map<String, Double> accForClassGuessers = new HashMap<>();
        for (Entry<String, Long> e : evalClassCounts.entrySet()) {
            accForClassGuessers.put(e.getKey(), e.getValue() / (double) exampleCorpusTest.size());
        }
        double accRandomGuesser = 1.0 / accForClassGuessers.size();

        // Train the classifier
        long time1 = System.currentTimeMillis();
        BayesianLearner learner = new BayesianLearner(classes);
        for (String[] trainingExample : exampleCorpusTrain) {
            learner.learnExample(trainingExample[0], trainingExample[1]);
        }
        BayesianClassifier classifier = learner.createClassifier();
        time1 = System.currentTimeMillis() - time1;
        System.out.println("Training took       : " + time1 + "ms");
       
        // Classify the evaluation corpus
        long time2 = System.currentTimeMillis();
        int tp = 0, errors = 0, id = 0;
        String result;
        List<String[]> fpDetails = new ArrayList<>();
        for (String[] evalExample : exampleCorpusTest) {
            result = classifier.classify(evalExample[1]);
            if (evalExample[0].equals(result)) {
                ++tp;
            } else {
                ++errors;
                fpDetails.add(new String[] { Integer.toString(id), evalExample[0], result });
            }
            ++id;
        }
        time2 = System.currentTimeMillis() - time2;
        System.out.println("Classification took : " + time2 + "ms");
        double accuracy = tp / (double) (tp + errors);
        double minAccuracy = 0L;
       
        System.out.println("Baseline classifiers: ");
        for (Entry<String, Double> baseResult : accForClassGuessers.entrySet()) {
            System.out.println(String.format("Always %-13s: %-7.5f", baseResult.getKey(), baseResult.getValue()));
        }
        System.out.println(String.format("Random guesser      : %-7.5f", accRandomGuesser));
        System.out.println(String.format("Your solution       : %-7.5f (%d tp, %d errors)", accuracy, tp, errors));
        if (fpDetails.size() > 0) {
            System.out.println("  Wrong classifications are:");
            for (int i = 0; i < Math.min(fpDetails.size(), 20); ++i) {
                System.out.print("    id=");
                System.out.print(fpDetails.get(i)[0]);
                System.out.print(" expected=");
                System.out.print(fpDetails.get(i)[1]);
                System.out.print(" result=");
                System.out.println(fpDetails.get(i)[2]);
            }
            if (fpDetails.size() > 20) {
                System.out.println("    ...");
            }
        }

        // Make sure that the students solution is better than all baselines
        for (Entry<String, Double> baseResult : accForClassGuessers.entrySet()) {
            if (baseResult.getValue() >= accuracy) {
                StringBuilder builder = new StringBuilder();
                builder.append("Your solution is not better than a classifier that always chooses the \"");
                builder.append(baseResult.getKey());
                builder.append("\" class.");
                System.out.println(builder.toString());
            }
        }
        if (accRandomGuesser >= accuracy) {
            System.out.println("Your solution is not better than a random guesser.");
        }
        if ((minAccuracy > 0) && (minAccuracy > accuracy)) {
            System.out.println("Your solution did not reach the expected accuracy of " + minAccuracy);
        }
        System.out.println("Test successfully completed.");
    }
}
