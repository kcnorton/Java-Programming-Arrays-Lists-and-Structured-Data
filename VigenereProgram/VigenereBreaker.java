import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        //initialize string builder
        StringBuilder sliceString = new StringBuilder();
        //iterate through message
        for(int i = whichSlice; i < message.length(); i += totalSlices){
            //build string
            sliceString.append(message.charAt(i));
        }
        return sliceString.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        //create CaesarCracker object
        CaesarCracker ccr = new CaesarCracker(mostCommon);
        //slice encrypted message into klength strings
        for(int i = 0; i < klength; i++){
            //slice string starting at i and for every klength character
            String slice = sliceString(encrypted,i,klength);
            //get key
            int currKey = ccr.getKey(slice);
            //add to key array
            key[i] = currKey;
        }
        return key;
    }

    public void breakVigenere () {
        //
        FileResource fr = new FileResource();
        String message = fr.asString();
        //read in multiple dictionary files
        DirectoryResource dCollection = new DirectoryResource();
        //create new HashMap for language and dictionaries
        HashMap<String,HashSet<String>> dMap = new HashMap<String,HashSet<String>>();
        //iterate over dictionary files to create HashMap
        for(File f : dCollection.selectedFiles()){
            //get filename
            String language = f.getName();
            //make fileresource to pass to readDictionary
            FileResource dFR = new FileResource(f);
            //make HashSet dictionary from file
            HashSet dictionary = readDictionary(dFR);
            //add to HashMap
            dMap.put(language,dictionary);
            System.out.println(language);
        }
        String decryption = breakForAllLangs(message,dMap);
        //System.out.println("The decrypted message is\t" + decryption.substring(0,100));
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        //make new HashSet of strings 
        HashSet dictionary = new HashSet<String>();
        //read in each line from fr
        for(String line : fr.lines()){
            //convert to lowercase and put line into hashset
            dictionary.add(line.toLowerCase());
        }
        //return hashset of words
        return dictionary;
    }
    
    public int countWords(String message, HashSet dictionary){
        //initialize count
        int count = 0;
        //lowercase message
        String lMessage = message.toLowerCase();
        //split message into words
        String[] words = lMessage.split("\\W+");
        //iterate over words
        for(String w : words){
            //count how many words are Real words
            if(dictionary.contains(w)){
                count++;
            }
        }
        //return the count
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet dictionary){
        //initialize max
        int max = 0;
        //initialize keyLegnth
        int keyLength = 0;
        //initialize maxMessage
        String maxMessage = "";
        //initialize decryption
        String decryption = "";
        //find most common character in dictionary
        char c = mostCommonCharIn(dictionary);
        //try key lengths from 1 to 100
        for(int i = 1; i < 101; i++){
            int[] key = tryKeyLength(encrypted,i,c);
            VigenereCipher vc = new VigenereCipher(key);
            //decrypt
            decryption = vc.decrypt(encrypted);
            //count how many Real words
            int count = countWords(decryption,dictionary);
            //compare largest number of real words
            if(count > max){
                max = count;
                maxMessage = decryption;
                keyLength = i;
            }
        }
        System.out.println(max);
        //return decryption with largest number of real words
        return maxMessage;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary){
        //find which character in the English alphabet occurs most often in dictionary
        //initialize alphabet
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        //initialize count
        int[] count = new int[alphabet.length()];
        //initialize max
        int max = 0;
        //initialize maxIndex
        int maxIndex = 0;
        //iterate over dictionary words and count occurence of each letter
        for(String s : dictionary){
            //make lowercase
            String lowerS = s.toLowerCase();
            //count occurrence of each letter
            for(int i = 0; i < alphabet.length(); i++){
                //check occurrence
                if(lowerS.contains(alphabet.substring(i,i+1))){
                    //add to count
                    count[i]++;
                    if(count[i] > max){
                        max = count[i];
                        maxIndex = i;
                    }
                }
            }
        }
        //initialize character c
        char common = alphabet.charAt(maxIndex);
        //return most commonly occurring character
        return common;
    }
    
    public String breakForAllLangs(String encrypted, HashMap<String,HashSet<String>> languages){
        //try breaking the encryption for each language and see which gives the best result
        //initialize max
        int max = 0;
        //initialize word count
        int count = 0;
        //initialize maxMessage
        String maxMessage = "";
        //initialize decryption
        String decryption = "";
        //initialize max language
        String maxLang = "";
        //iterate over hashset languages
        for(String l : languages.keySet()){
            //get dictionary for language
            HashSet<String> dictionary = languages.get(l);
            //break for language
            decryption = breakForLanguage(encrypted,dictionary);
            //count words
            count = countWords(decryption,dictionary);
            //check if count is larger than max count
            if(count > max){
                //replace max with count
                max = count;
                maxMessage = decryption;
                maxLang = l;
            }
        }
        System.out.println("The language is " + maxLang);
        return maxMessage.substring(0,100);
    }
    
    
    
    
    
    
    
    
    
    
}
