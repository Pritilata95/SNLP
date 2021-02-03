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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set; 
/**
 *
 * @author ASUS
 */

/**
 * Learner (or Builder) class for a naive Bayes multilabel classifier.
 */
public class MultiLabelLearner {
    // YOUR CODE HERE

    /**
     * Constructor taking the number of classes the classifier should be able to
     * distinguish.
     */ 
    Map <String, Integer> classfrequency = new HashMap <String, Integer>();
    Set <String> vocabulary = new HashSet<String>();
    Map<String, Map<String, Integer>> wordfrequency=new HashMap<String, Map<String, Integer>>();
    Set<String> classes = new HashSet<String>();
    MultiLabelLearner(Set<String> classes) {
        // YOUR CODE HERE
        for(String s : classes){
            classfrequency.put(s,0);
            wordfrequency.put(s, new HashMap());
        }
        this.classes=classes;
     }
    /**to get index of a value in a set
    private int getIndex(Set<? extends Object> set, Object value) {
    int result = 0;
    for (Object entry:set) {
        if (entry.equals(value)) return result;
        result++;
    }
    return -1;
    }**/
    //words that will be removed from wordset
    private final Set<String> stopwords = new HashSet<>(Arrays.asList("i","me","my","myself","we","our","ours","ourselves","you","your","yours",
    "yourself","yourselves","he","him","his","himself","she","her","hers","herself","it","its","itself","they","them",
    "their","theirs","themselves","what","which","who","whom","this","that","these","those","am","is","are","was",
    "were","be","been","being","have","has","had","having","do","does","did","doing","a","an","the","and","but","if",
    "or","because","as","until","while","of","at","by","for","with","about","against","between","into","through",
    "during","before","after","above","below","to","from","up","down","in","out","on","off","over","under","again",
    "further","then","once","here","there","when","where","why","how","all","any","both","each","few","more","most",
    "other","some","such","no","nor","not","only","own","same","so","than","too","very","s","t","can","will","just",
    "don","should","now","reuter","today","tomorrow","yesterday","day","previous","next","after","before"));
    /* 
    this method preprocess text and get words
    */
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
     * The method used to learn the training examples. It takes the names of the
     * classes as well as the text of the training document.
     */
    public void learnExample(Set<String> classes, String text) {
        // YOUR CODE HERE  
        List<String> words = preprocess(text);
        for(String clazz : classes){
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
    }
    /**
     * Creates a MultiLabelClassifier instance based on the statistics gathered from
     * the training example.
     */
    public MultiLabelClassifier createClassifier() {
        MultiLabelClassifier classifier = null;
        // YOUR CODE HERE
        //finding vocabualry
        for(String s : wordfrequency.keySet()){
             Map <String, Integer> wf = (Map <String, Integer>) wordfrequency.get(s);
             vocabulary.addAll(wf.keySet());
        }
        classifier = new MultiLabelClassifier(vocabulary.size(),classes,classfrequency,wordfrequency,stopwords);
        return classifier;
    }
    
    public void myprint(){
        for(String s : classfrequency.keySet()){ 
            System.out.println("class: "+s +"\tclassfrequency: "+classfrequency.get(s));
            System.out.println("class: "+s +"\tclassvocab: "+wordfrequency.get(s)+"\tsize: "+wordfrequency.get(s).size()); 
        }
        createClassifier();
        System.out.println("vocabsize : "+ vocabulary.size());
    }
    
}
