 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BigramModel;

/**
 *
 * @author Pritilata Saha
 */
//import java.util.Collections;
//import java.util.LinkedHashSet;
//import java.util.List;
import java.util.stream.Collectors;
import java.util.*;

/**
 * Simple implementation of a bi-gram matrix which can normalize itself and
 * apply Laplace smoothing to its inner counts.
 */
public class BigramMatrix {
    /**
     * Token used for the start of a sentence.
     */
    public static final String SENTENCE_START = "<s>";
    /**
     * Token used for the end of a sentence.
     */
    public static final String SENTENCE_END = "</s>";
    
    // YOUR CODE HERE
    
    int initialization = 0;
    LinkedHashSet<String> vocabulary = new LinkedHashSet<String>();
    ArrayList<String> Vocabulary;
    double[][] rawbigrammatrix ;
    double[][] nbigrammatrix ;
    double[][] bigrammatrix ;
    int[] unigrammatrix; 
    boolean normalize = false;
    boolean LaplaceSmoothing = false;
    /**
     * Constructor.
     */
    public BigramMatrix(List<String> tokens) {
        create(tokens);
    }

    /**
     * Internal method for creating the bi-gram matrix from a given list of tokens.
     * 
     * @param tokens
     *            the tokens of an input text for which the matrix should be
     *            initialized.
     */
    protected void create(List<String> tokens) {
        // YOUR CODE HERE
        vocabulary.addAll(tokens);
        rawbigrammatrix = new double[vocabulary.size()][vocabulary.size()]; 
        bigrammatrix = new double[vocabulary.size()][vocabulary.size()];
        unigrammatrix = new int[vocabulary.size()];
        //System.out.println(vocabulary);      
        for (String s : vocabulary){
            unigrammatrix[initialization] = Collections.frequency(tokens, s);
            //System.out.println(unigrammatrix[initialization]);
            initialization++;
        }
        Vocabulary= new ArrayList<String>(vocabulary);
        //System.out.println(Vocabulary);
	for (int i = 0; i < tokens.size() - 1; i++) {
            String Token1 = tokens.get(i);
            int index1 = Vocabulary.indexOf(Token1);
            String Token = tokens.get(i + 1);
            int index = Vocabulary.indexOf(Token);
            rawbigrammatrix[index1][index] += 1;
	}
        for(int i = 0; i<rawbigrammatrix.length;i++){
            double[] arawbigrammatrix = rawbigrammatrix[i];
            int aLength = arawbigrammatrix.length;
            System.arraycopy(arawbigrammatrix,0,bigrammatrix[i] , 0, aLength);
        }
        

    }

    /**
     * Transforms the internal count matrix into a normalized counts matrix.
     */
    public void normalize() {
        // YOUR CODE HERE
        nbigrammatrix = new double[vocabulary.size()][vocabulary.size()];
        for(int i = 0; i < vocabulary.size() ; i++){
            for(int j = 0; j < vocabulary.size() ; j++){
                nbigrammatrix[i][j] = rawbigrammatrix[i][j]/unigrammatrix[i];
                //System.out.print(String.format("%.2f",rawbigrammatrix[i][j])+"\t");
            }
            //System.out.println();
        }    
        normalize = true;
        

    }

    /**
     * Performs the Laplace smoothing on the bi-gram matrix.
     */
    public void performLaplaceSmoothing() {
        // YOUR CODE HERE
        /*if (normalize){
            for(int i = 0; i < vocabulary.size() ; i++){
                for(int j = 0; j < vocabulary.size() ; j++){
                    bigrammatrix[i][j] = (rawbigrammatrix[i][j]+1)/(unigrammatrix[i]+vocabulary.size());
                    //System.out.print(String.format("%.2f",bigrammatrix[i][j])+"\t");
                }
                //System.out.println();
            }
        }*/
        //else{
            for(int i = 0; i < vocabulary.size() ; i++){
                for(int j = 0; j < vocabulary.size() ; j++){
                    bigrammatrix[i][j] = (rawbigrammatrix[i][j]+1);
                    //System.out.print(String.format("%.2f",bigrammatrix[i][j])+"\t");
                }
            //System.out.println();
            }
        //}
        LaplaceSmoothing = true;

        
    }

    /**
     * Returns the count of the bi-gram matrix for the bi-gram (word1, word2).
     */
    public double getCount(String word1, String word2) {
        double count = 0;
        // YOUR CODE HERE
        if(Vocabulary.contains(word1)&& Vocabulary.contains(word2)){  
            int word1pos = Vocabulary.indexOf(word1);
            int word2pos = Vocabulary.indexOf(word2);
            count = bigrammatrix[word1pos][word2pos];            
        } 
        else {   
            if(LaplaceSmoothing)
                count = 1;
            else 
                count = 0;
        }
        /*int word1pos = Vocabulary.indexOf(word1);
        int word2pos = Vocabulary.indexOf(word2);
        count = bigrammatrix[word1pos][word2pos];*/
        
        return count;
    }

    /**
     * Returns the normalized count of the bi-gram matrix for the bi-gram (word1, word2) (i.e., P(word2 | word1)).
     */
    public double getNormalizedCount(String word1, String word2) {
        double normalizedCount = 0;
        // YOUR CODE HERE
        if(Vocabulary.contains(word1)&& Vocabulary.contains(word2)){  
            if (LaplaceSmoothing){
                            for(int i = 0; i < vocabulary.size() ; i++){
                for(int j = 0; j < vocabulary.size() ; j++){
                    nbigrammatrix[i][j] = (rawbigrammatrix[i][j]+1)/(unigrammatrix[i]+vocabulary.size());
                    //System.out.print(String.format("%.2f",bigrammatrix[i][j])+"\t");
                }
                //System.out.println();
                //normalizedCount = getCount(word1,word2); 
                            }
                            
                            }     
            
                int word1pos = Vocabulary.indexOf(word1);
                int word2pos = Vocabulary.indexOf(word2);
                normalizedCount = nbigrammatrix[word1pos][word2pos];
            
        }
        else{
            if (LaplaceSmoothing){
                if(Vocabulary.contains(word1)){
                    double sum =Vocabulary.size();
                    int word1pos = Vocabulary.indexOf(word1);
                    for(int i = 0; i<Vocabulary.size();i++){
                        
                        sum= sum + rawbigrammatrix[word1pos][i];
                        System.out.print(rawbigrammatrix[word1pos][i]+ "\t");
                    }
                    System.out.println("\n"+sum);
                    normalizedCount = (1/sum);
                }
                else{
                    double sum = Vocabulary.size();
                    normalizedCount = (1/sum);
                }
            }
            else
                normalizedCount = 0;
            
        }

        
        return normalizedCount;
    }
}

// This line should make sure that compile errors are directly identified when executing this cell
// (the line itself does not produce any meaningful result)
//(new BigramMatrix(Arrays.asList("a", "b"))).normalize();