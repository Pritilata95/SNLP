/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NaiveBayesClassifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author ASUS
 */
/**
 * Learner (or Builder) class for a naive Bayes classifier.
 */

public class BayesianLearner {
    // YOUR CODE HERE
    Map <String, Integer> classfrequency = new HashMap <String, Integer>();
    Set <String> vocabulary = new HashSet<String>();
    Map<String, Map<String, Integer>> wordfrequency=new HashMap<String, Map<String, Integer>>();

    /**
     * Constructor taking the set of classes the classifier should be able to
     * distinguish.
     */
    
    public BayesianLearner(Set<String> classes) {
        // YOUR CODE HERE
       	for (String clazz : classes) {
            classfrequency.put(clazz, 0);
            wordfrequency.put(clazz, new HashMap());
	}
        
    }
    private final Set<String> stopwords = new HashSet<>(Arrays.asList("i","me","my","myself","we","our","ours","ourselves","you","your","yours",
    "yourself","yourselves","he","him","his","himself","she","her","hers","herself","it","its","itself","they","them",
    "their","theirs","themselves","what","which","who","whom","this","that","these","those","am","is","are","was",
    "were","be","been","being","have","has","had","having","do","does","did","doing","a","an","the","and","but","if",
    "or","because","as","until","while","of","at","by","for","with","about","against","between","into","through",
    "during","before","after","above","below","to","from","up","down","in","out","on","off","over","under","again",
    "further","then","once","here","there","when","where","why","how","all","any","both","each","few","more","most",
    "other","some","such","no","nor","not","only","own","same","so","than","too","very","s","t","can","will","just",
    "don","should","now","reuter","today","tomorrow","yesterday","day","previous","next","after","before"));

    /**
     * This method preprocesses the document text
     **/
    public List<String> preprocess(String text){
    	List<String> tokens = new ArrayList<String>(); 
	String newtext = text.replaceAll("[^a-zA-Z0-9]"," ").toLowerCase();
        newtext = newtext.replaceAll("[0-9]+", "#DIGIT");
	String text_array[] = newtext.split("\\s+");
        tokens.addAll(Arrays.asList(text_array));
        for(int i=0; i < tokens.size(); i++){
            if(stopwords.contains(tokens.get(i))){
                tokens.remove(tokens.get(i));
            }
                
        }
    	return tokens;
    }

    /**
     * The method used to learn the training examples. It takes the name of the
     * class as well as the text of the training document.
     */
    public void learnExample(String clazz, String text) {
        // YOUR CODE HERE
        List<String> words = preprocess(text);
        int val = (classfrequency.get(clazz)) +1;
        classfrequency.put(clazz, val);
        Map <String, Integer> wf = wordfrequency.get(clazz);
        for(String w : words){
            if(!wf.containsKey(w))
                wf.put(w, 1);
            else{
                int valw = (wf.get(w))+1;
                    wf.put(w, valw);
            }
            }   
        }

    
    /**
     * Creates a BayesianClassifier instance based on the statistics gathered from
     * the training example.
     */
    public BayesianClassifier createClassifier() {
        BayesianClassifier classifier = null;
        // YOUR CODE HERE
        //finding vocabualry
        for(Map.Entry m : wordfrequency.entrySet()){
             Map <String, Integer> wf = (Map <String, Integer>) m.getValue();
             vocabulary.addAll(wf.keySet());
        }
        classifier = new BayesianClassifier(vocabulary,classfrequency,wordfrequency);
        return classifier;
    }
    
    /**
     for print to be deleted
     **/
    /*public void myprint(){
        System.out.println("---------Class Frequency-----------");
        for(Map.Entry m : classfrequency.entrySet()){    
             System.out.println(m.getKey()+" "+m.getValue());    
        }
        System.out.println("---------Word Frequency-----------");
        for(Map.Entry m : wordfrequency.entrySet()){ 
             System.out.println("Class name:  "+m.getKey()+ " ");
             //System.out.println(m.getKey()+ " ");
             Map <String, Integer> wf = (Map <String, Integer>) m.getValue();
             for(Map.Entry n :  wf.entrySet()){ 
             System.out.println(n.getKey()+" "+n.getValue());} 
             System.out.println( );
        }
        System.out.println(vocabulary.size());
        System.out.println(vocabulary);
    }*/
}
