/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NaiveBayesMultilevel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors; 

/**
 *
 * @author ASUS
 */
public class main {
    public static void main(String args[]){
        double f1 = 0;
        double minF1Score=0;
        System.out.println("---------- Simple example corpus ----------");
        List<ClassifiedDocument> exampleCorpusTrain = Arrays.asList(
            new ClassifiedDocument(new HashSet<String>(Arrays.asList("chess")),
                    "white king, black rook, black queen, white pawn, black knight, white bishop."),
            new ClassifiedDocument(new HashSet<String>(Arrays.asList("history")),
                    "knight person granted honorary title knighthood"),
            new ClassifiedDocument(new HashSet<String>(Arrays.asList("history")),
                    "knight order eligibility, knighthood, head of state, king, prelate, middle ages."),
            new ClassifiedDocument(new HashSet<String>(Arrays.asList("chess", "game")),
                    "Defense knight king pawn opening game opponent."),
            new ClassifiedDocument(new HashSet<String>(Arrays.asList("game")),
                    "Game. player opponent victory. draw."));
        List<ClassifiedDocument> exampleCorpusTest = Arrays.asList(
            new ClassifiedDocument(new HashSet<String>(Arrays.asList("history")), "Knighthood Middle Ages."),
            new ClassifiedDocument(new HashSet<String>(Arrays.asList("game", "chess")),
                    "player black knight opponent pawn queen checkmate game draw victory."),
            // document with unknown words
            new ClassifiedDocument(new HashSet<String>(Arrays.asList("game")), "player opponent opening"));  
        // Determine the classes
            Set<String> classes = Arrays.asList(exampleCorpusTrain, exampleCorpusTest).stream().flatMap(l -> l.stream())
            .map(d -> d.classes).flatMap(c -> c.stream()).distinct().collect(Collectors.toSet());
            System.out.println(classes);
                // Determine the number of instances per class in the evaluation set
                Map<String, Long> evalClassCounts = exampleCorpusTest.stream().map(d -> d.classes).flatMap(c -> c.stream())
                        .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
                for (String clazz : classes) {
                    if (!evalClassCounts.containsKey(clazz)) {
                        evalClassCounts.put(clazz, 0L);
                    }
                }
                long expectedClassSum = evalClassCounts.entrySet().stream().mapToLong(e -> e.getValue()).sum();

                // Determine the expected accuracies of the baselines
                Map<String, double[]> f1ForClassGuessers = new HashMap<>();
                for (Entry<String, Long> e : evalClassCounts.entrySet()) {
                    f1ForClassGuessers.put(e.getKey(), calcStats(e.getValue().intValue(),
                            exampleCorpusTest.size() - e.getValue().intValue(), (int) (expectedClassSum - e.getValue())));
                }

                // Train the classifier
                long time1 = System.currentTimeMillis();
                MultiLabelLearner learner = new MultiLabelLearner(classes);
                /*/to be deleted
                System.out.println("testing");
                for (ClassifiedDocument trainingExample : exampleCorpusTrain) {
                    learner.learnExample(trainingExample.classes, trainingExample.text);
                }
                learner.myprint();*///delete
                for (ClassifiedDocument trainingExample : exampleCorpusTrain) {
                    learner.learnExample(trainingExample.classes, trainingExample.text);
                }
                MultiLabelClassifier classifier = learner.createClassifier();
                time1 = System.currentTimeMillis() - time1;
                System.out.println("Training took       : " + time1 + "ms");

                // Classify the evaluation corpus
                long time2 = System.currentTimeMillis();
                Map<String, int[]> classCounts = new HashMap<>();
                final int TP = 0, FP = 1, FN = 2, TN = 3;
                for (String clazz : classes) {
                    classCounts.put(clazz, new int[4]);
                }
                int id = 0;
                Set<String> result;
                List<String[]> errorDetails = new ArrayList<>();
                boolean added;
                for (ClassifiedDocument evalExample : exampleCorpusTest) {
                    added = false;
                    result = classifier.classify(evalExample.text);
                    String resultAsString = result.toString();
                    for (String clazz : classes) {
                        if (evalExample.classes.contains(clazz)) {
                            if (result.contains(clazz)) {
                                ++classCounts.get(clazz)[TP];
                            } else {
                                ++classCounts.get(clazz)[FN];
                                if (!added) {
                                    errorDetails.add(new String[] { Integer.toString(id), evalExample.classes.toString(),
                                            resultAsString });
                                }
                            }
                        } else {
                            if (result.contains(clazz)) {
                                ++classCounts.get(clazz)[FP];
                                if (!added) {
                                    errorDetails.add(new String[] { Integer.toString(id), evalExample.classes.toString(),
                                            resultAsString });
                                }
                            } else {
                                ++classCounts.get(clazz)[TN];
                            }
                        }
                    }
                    result.removeAll(evalExample.classes);
                    if ((result.size() > 0) && (!added)) {
                        errorDetails.add(
                                new String[] { Integer.toString(id), evalExample.classes.toString(), result.toString() });
                    }
                    ++id;
                }
                time2 = System.currentTimeMillis() - time2;
                System.out.println("Classification took : " + time2 + "ms");
                int counts[] = new int[4];
                for (Entry<String, int[]> stats : classCounts.entrySet()) {
                    counts[0] += stats.getValue()[0];
                    counts[1] += stats.getValue()[1];
                    counts[2] += stats.getValue()[2];
                    counts[3] += stats.getValue()[3];
                }
                double solutionPerformance[] = calcStats(counts[TP], counts[FP], counts[FN]);

                System.out.println("classifiers           precision    recall  f1-score");
                for (Entry<String, double[]> baseResult : f1ForClassGuessers.entrySet()) {
                    System.out.println(String.format("Always %-13s:   %-7.5f   %-7.5f   %-7.5f", baseResult.getKey(),
                            baseResult.getValue()[0], baseResult.getValue()[1], baseResult.getValue()[2]));
                }
                System.out.println(
                        String.format("Your solution       :   %-7.5f   %-7.5f   %-7.5f (%d tp, %d tn, %d fp, %d fn)",
                                solutionPerformance[0], solutionPerformance[1], solutionPerformance[2], counts[TP],
                                counts[TN], counts[FP], counts[FN]));
                f1 = solutionPerformance[2];
                if (errorDetails.size() > 0) {
                    System.out.println("  Wrong classifications are:");
                    for (int i = 0; i < Math.min(errorDetails.size(), 20); ++i) {
                        System.out.print("    id=");
                        System.out.print(errorDetails.get(i)[0]);
                        System.out.print(" expected=");
                        System.out.print(errorDetails.get(i)[1]);
                        System.out.print(" result=");
                        System.out.println(errorDetails.get(i)[2]);
                    }
                    if (errorDetails.size() > 20) {
                        System.out.println("    ...");
                    }
                }

                // Make sure that the students solution is better than all baselines
                for (Entry<String, double[]> baseResult : f1ForClassGuessers.entrySet()) {
                    if (baseResult.getValue()[2] >= solutionPerformance[2]) {
                        StringBuilder builder = new StringBuilder();
                        builder.append("Your solution is not better than a classifier that always chooses the \"");
                        builder.append(baseResult.getKey());
                        builder.append("\" class.");
                        System.out.println(builder.toString());
                    }
                }
                if ((minF1Score > 0) && (minF1Score > solutionPerformance[2])) {
                    System.out.println("Your solution did not reach the expected F1-score of " + minF1Score);
                }
                System.out.println("Test successfully completed.");
            }
    /**
    * Simple method for calculating micro precision, recall and F1-measure.
    */
    public static double[] calcStats(int tp, int fp, int fn) {
        double precision = tp / (double) (tp + fp);
        double recall = tp / (double) (tp + fn);
        return new double[] { precision, recall, (2 * precision * recall) / (precision + recall) };
    } 
}