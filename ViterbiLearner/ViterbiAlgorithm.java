/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViterbiLearner;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author ASUS
 */
/**
 * A class implementing the Viterbi algorithm based on an Hidden Markov Model
 * with the given states, observations, transition matrix and emission matrix.
 * 
 * You may want to copy it from Exercise-1.
 */
public class ViterbiAlgorithm {
    // YOUR CODE HERE
    String states[];
    String observationVocab[];
    double[][] transitionMatrix;
    double emissionMatrix[][];
    /**
     * Constructor.
     */
    public ViterbiAlgorithm(String[] states, String[] observationVocab, double[][] transitionMatrix,
            double[][] emissionMatrix) {
        // YOUR CODE HERE
	this.states = states;
	this.observationVocab = observationVocab;
	this.transitionMatrix = transitionMatrix;
	this.emissionMatrix = emissionMatrix;          
    }

    /**
     * Returns the sequence of states which has the highest probability to create
     * the given sequence of observations.
     * 
     * @param observations
     *            a sequence of observations
     * @return the sequence of states
     */
    public StateSequence getStateSequence(String[] observations) {
        StateSequence sequence = null;
        // YOUR CODE HERE
        double temp;
        double[][] viterbi = new double[states.length+2][observations.length];
        int[][] backpointer = new int[states.length+2][observations.length];
        List<String> observationVocablist=Arrays.asList(observationVocab);
        for(int s=0;s<states.length;s++){
            viterbi[s][0]=Math.log(transitionMatrix[0][s+1])+ Math.log(emissionMatrix[s][observationVocablist.indexOf(observations[0])]);
            //backpointer[s][0]=0;
        }
        for(int t=1;t<observations.length;t++){
            for(int s=0;s<states.length;s++){
                for(int s1=0;s1<states.length;s1++){
                   temp = viterbi[s1][t-1]+Math.log(transitionMatrix[s1+1][s+1])+Math.log(emissionMatrix[s][observationVocablist.indexOf(observations[t])]);
                   if(viterbi[s][t]!=0){
                        if(viterbi[s][t]<temp){
                            viterbi[s][t]=temp;
                            backpointer[s][t]=s1;
                        }
                   }
                   else{
                        viterbi[s][t]=temp;
                        backpointer[s][t]=s1;
                    }
                }
            }
        }
        //final
        temp = -Double.MAX_VALUE;
        for(int s=0;s<states.length;s++){
            viterbi[states.length][observations.length-1] = viterbi[s][observations.length-1]+Math.log(transitionMatrix[s+1][states.length+1]);
            if(viterbi[states.length][observations.length-1]>temp){
                temp=viterbi[states.length][observations.length-1];
                backpointer[states.length][observations.length-1]= s;
            }
        }

        String[] transtate=new String[observations.length+1];
        int currentstate=backpointer[states.length][observations.length-1];
        transtate[observations.length]=states[currentstate];
        for(int t =observations.length-1;t>0;t--){
            currentstate=backpointer[currentstate][t];
            transtate[t]= states[currentstate];
        }
        String[] str = new String[observations.length];
	for (int i = 0; i < str.length; i++) {
            str[i] = transtate[i + 1];
	}
        StateSequence sequences = new StateSequence(str,temp);
        return sequences;  
    }
}
