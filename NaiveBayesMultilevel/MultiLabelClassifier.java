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
import java.util.Set;

/**
 *
 * @author ASUS
 */
public class MultiLabelClassifier {
    // YOUR CODE HERE
    int vocabsize;
    Map<String, Double> prior=new HashMap();
    Map<String, Map<String, Integer>> wordfrequency;
    Set<String> classes = new HashSet<String>();
    Set<String> stopwords;
    MultiLabelClassifier(int totalvocab,Set<String> classes,Map <String, Integer> classfrequency,Map<String, Map<String, Integer>> wordfrequency,Set<String> stopwords){
        vocabsize=totalvocab;
        int k = classfrequency.values().stream().mapToInt(Integer :: intValue).sum();
        double kd = k; 
        for(String m : classfrequency.keySet()){
            double val = (double)classfrequency.get(m); 
            prior.put(m , val/kd);
        }
        this.wordfrequency=wordfrequency;
        this.classes=classes;
        this.stopwords=stopwords;
    }
    /**
     * Classifies the given document and returns the class names.
     */
    //words that will be removed from wordset
    /*private final Set<String> stopwords = new HashSet<>(Arrays.asList("i","me","my","myself","we","our","ours","ourselves","you","your","yours",
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
    
    public Set<String> classify(String text) {
        Set<String> results = new HashSet<String>();
        // YOUR CODE HERE
        Map<String,Double>classprobmap=new HashMap();
        double temp=Double.NEGATIVE_INFINITY;
        String clazz = new String();
        List<String> l = preprocess(text);
        for(String s : classes){
            double likelihood=0L;
            double classprob=0L;
            Map<String, Integer> wf = wordfrequency.get(s);
            int n = wf.values().stream().mapToInt(Integer :: intValue).sum(); 
            int nk;
            for(String s1 : l){
                try{
                    nk = wf.get(s1);
//                    System.out.println(s1+" : "+nk);
                }
                catch(NullPointerException e){
                    nk = 0;
//                    System.out.println(s1+" : "+nk);
                }
                likelihood += Math.log((double)(nk+1)/(n+vocabsize));
//                System.out.println("likelihood : " + likelihood);
            }
            classprob = Math.log(prior.get(s))+likelihood;
            classprobmap.put(s, classprob);
        }
//        System.out.println("new text");
        for(String s : classprobmap.keySet()){
//            System.out.println(s + "   " +classprobmap.get(s) );
            if(classprobmap.get(s) > temp){
                temp=classprobmap.get(s);
                clazz=s;                
            }
        }
        results.add(clazz);
        for(String s : classprobmap.keySet()){
            if(temp - classprobmap.get(s) < 1.5){ 
                results.add(s);
            }
        }
 //       System.out.println("chossen one");
//        System.out.println("Result: "+ results);
        return results;
    }
    
}
