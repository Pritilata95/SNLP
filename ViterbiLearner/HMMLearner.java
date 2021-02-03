/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViterbiLearner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author ASUS
 */
public class HMMLearner {

    // YOUR CODE HERE
    Map<String,Integer> trans = new HashMap(); 
    Map<String,Integer> obs = new HashMap();
    Map<String,Integer> statecount = new HashMap();
    double[][] transitionMatrix;
    double emissionMatrix[][];
    String[] states;
    Set<String> states1=new HashSet<>();
    int linecount=0;   
    String[] observationVocab;
    Set<String>observationVocabSet = new HashSet<>();
    
    public HMMLearner(int numberOfStates, int sizeOfVocab) {
        // YOUR CODE HERE
        transitionMatrix=new double[numberOfStates+2][numberOfStates+2];
        emissionMatrix=new double[numberOfStates][sizeOfVocab];
        observationVocab = new String[sizeOfVocab];
    }

    public void processSequence(String sequence) {
        // YOUR CODE HERE 
        linecount=linecount+1; 
        sequence="START "+sequence+" END"; 
        String[] seq = sequence.split(" "); 
        for(int i=0;i<seq.length-1;i++){           
            int val,val1,val2;
            String key="",key1="",key2="";
            String s[]=seq[i].split("_");
            String s1[]=seq[i+1].split("_");
            if(i!=0 && i!=(seq.length-2)){ 
                key= s[1]+"_"+s1[1];               
            }
            else if(i==0){
                key= s[0]+"_"+s1[1]; 
            }
            else if(i==(seq.length-2)){
                key= s[1]+"_"+s1[0]; 
            }
            if(trans.containsKey(key)){
                val=trans.get(key)+1;
                trans.put(key,val);
            }
            else{
                    trans.put(key,1);
                }
 
            if((i+1)<(seq.length-1)){
                key1=seq[i+1];
                observationVocabSet.add(key1.split("_")[0]);
                if(obs.containsKey(key1)){
                    val1=obs.get(key1)+1;
                    obs.put(key1, val1);
                }
                else{obs.put(key1, 1);}
                key2=seq[i+1].split("_")[1];
                states1.add(key2);
                if(statecount.containsKey(key2)){
                    val2=statecount.get(key2)+1;
                    statecount.put(key2, val2);
                }
                else{statecount.put(key2, 1);}
            }
        }
        
    }
    
    public void myprint(){ 
        for(String i: trans.keySet()){  
            System.out.print(i+": "+ trans.get(i)+"\n");
        }
        System.out.println();
        for(String j: obs.keySet()){  
            System.out.print(j+": "+ obs.get(j)+"\n");
        }
        System.out.println();
        for(String k: statecount.keySet()){  
            System.out.print(k+": "+ statecount.get(k)+"\n");
        }
        System.out.println();
        for(int i=0;i<transitionMatrix.length;i++){
            for(int j=0;j<transitionMatrix[0].length;j++){
                System.out.print(String.format("%.4f", transitionMatrix[i][j])+"\t\t");
            }
            System.out.println();
        }
        for(int i=0;i<emissionMatrix.length;i++){
            for(int j=0;j<emissionMatrix[0].length;j++){
                System.out.print(String.format("%.4f", emissionMatrix[i][j])+"\t\t");
            }
            System.out.println();
        }
    }
 
    public ViterbiAlgorithm buildViterbi() {
        ViterbiAlgorithm viterbi = null;
        // YOUR CODE HERE
        states=states1.toArray(String[]::new); 
        observationVocab=observationVocabSet.toArray(String[]::new); 
        for(int i=0;i<states.length;i++) { 
            int denominator = statecount.get(states[i]);
             transitionMatrix[0][i+1] = (double) trans.get( "START"+"_"+states[i]) / linecount;
             transitionMatrix[i+1][states.length+1] = (double) trans.get(states[i]+"_"+"END") / denominator;
            for(int j=0;j<states.length;j++)
                transitionMatrix[i+1][j+1] = (double) trans.get(states[i]+"_"+states[j]) / denominator;        
        }
        for(int i=0;i<states.length;i++) {
            for(int j=0;j<observationVocab.length;j++){
                emissionMatrix[i][j] = (double) obs.get(observationVocab[j]+"_"+states[i]) / statecount.get(states[i]);
            } 
        }
        viterbi = new ViterbiAlgorithm(states, observationVocab, transitionMatrix, emissionMatrix);
        return viterbi;
    }
}
