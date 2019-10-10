
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;

public class CodonCount {
    //fields
    private HashMap<String,Integer> codonMap;
    
    //constructor
    public CodonCount(){
        codonMap = new HashMap<String,Integer>();
    }
    //methods
    public void buildCodonMap(int start, String dna){
        //build a new map of codons mapped to their counts
        //reading from with the position start
        codonMap.clear();
        //check that next three is not larger than remaining dna substring
        while(start <= (dna.length() - 3)){
            //codon substring
            String codon = dna.substring(start, start + 3);
            //check if codon is in hashmap
            if(codonMap.containsKey(codon)){
                //add count
                int freqs = codonMap.get(codon);
                codonMap.put(codon,freqs+1);
            }
            else{
                //put in hashmap
                codonMap.put(codon,1);
            }
            //increase start by three
            start += 3;
        }
        return;
    }
    
    public String getMostCommonCodon(){
        //return the codon in a reading frame that has largest count
        //initialize count
        int count = 0;
        //initialize max
        int max = 0;
        //initialize max key
        String maxCodon = "";
        //iterate over keys
        for(String s : codonMap.keySet()){
            //get value
            count = codonMap.get(s);
            //check if count is larger than max
            if(count > max){
                //replace max with count
                max = count;
                maxCodon = s;
            }
        }
        return maxCodon;
    }
    
    public void printCodonCounts(int start, int end){
        //prints all codons in hashmap and their counts if their counts are between start and end inclusive
        for(String s : codonMap.keySet()){
            //if key value is between start and end inclusive
            if((codonMap.get(s) >= start) && (codonMap.get(s) <= end)){
                //print pair
                System.out.println(s + " " + codonMap.get(s));
            }
        }
    }
    
    public void tester(){
        //
        FileResource fr = new FileResource();
        String dna = fr.asString();
        dna = dna.toUpperCase().trim();
        buildCodonMap(2,dna);
        String common = getMostCommonCodon();
        System.out.println("The most common codon is " + common);
        printCodonCounts(1,50);
        
    }
    
    
    
    
    
}
