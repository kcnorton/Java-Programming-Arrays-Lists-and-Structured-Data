
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class WordLengths {
    //how many words from a file are of each possible length
    //group all words of length 30 or more together
    //don't count basic punctuation that are the first or last character
    public void countWordLengths(FileResource resource, int[] counts){
        //iterate over each word
        for(String s : resource.words()){
            //find last index of counts
            int lastIndex = counts.length;
            //make string word lowercase
            String sLower = s.toLowerCase();
            //check if the first character is punctuation
            if((sLower.charAt(0) < 'a') && (sLower.charAt(0) > 'z')){
                    sLower = sLower.substring(1,sLower.length());
            }
            //check if last character is punctuation
            if((sLower.charAt(sLower.length()-1) < 'a') || (sLower.charAt(sLower.length()-1) > 'z')){
                sLower = sLower.substring(0,sLower.length()-1);
            } 
            //count length
            int length = sLower.length();
            //check if length is within counts[]
            if(lastIndex < length){
                //add to last index
                counts[lastIndex] += 1;
            } else {
                //add to index
                counts[length] += 1;
            }
        }
        return;
    }
    
    public void testCountWordLengths(){
        //create fileresource to select file
        FileResource fr = new FileResource();
        //create counts int[] of size 31
        int[] counts = new int[31];
        //call countWord Lengths
        countWordLengths(fr, counts);
        for(int i = 0; i < counts.length; i++){
            System.out.println(counts[i] + " words of length: " + i);
        }
        //call indexOfMax
        int index = indexOfMax(counts);
        System.out.println("Index of max is " + index);
    }
    
    public int indexOfMax(int[] values){
        //set largest to zero to keep track of largest value
        int max = 0;
        //initialize index of large
        int indexOfMax = 0;
        //find last index of values
        int lastIndex = values.length;
        //index position of largest element in values
        for(int i = 0; i < (lastIndex); i++){
            if(values[i] > max){
                max = values[i];
                indexOfMax = i;
            }
        }
        //return index
        return indexOfMax;
    }
    
    
    
    
    
    
    
}
