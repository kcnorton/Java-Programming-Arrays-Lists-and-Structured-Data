
/**
 * Write a description of decrypt here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class decrypt {
    public int[] countLetters(String input){
        //counting occurrences
        int[] counts = new int[26];
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for(int i = 0; i < input.length(); i++){
            char ch = Character.toUpperCase(input.charAt(i));
            int dex = alphabet.indexOf(ch);
            if(dex != -1){
                counts[dex] += 1;
            }
        }
        return counts;
    }
    
    public int maxIndex(int[] values){
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
    
    public String decrypt(String input) {
        //get key for decryption
        int dkey = getKey(input);
        //Your answer is the String inside of encrypted
        return encrypt(input,26-dkey);
    }
    
    public String encrypt(String input, int key) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //Compute the shifted alphabet
        String shiftedAlphabet = alphabet.substring(key)+
        alphabet.substring(0,key);
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //make currChar uppercase
            char currCharUpper = Character.toUpperCase(currChar);
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(currCharUpper);
            //If currChar is in the alphabet
            if(idx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedAlphabet.charAt(idx);
                //check if currChar is uppercase
                if(currChar == alphabet.charAt(idx)){
                    //Replace the ith character of encrypted with newChar
                    encrypted.setCharAt(i, newChar);
                } else {
                    //otherwise replace the ith character of encrypted with lowercase newChar
                    encrypted.setCharAt(i, Character.toLowerCase(newChar));
                }
            }
            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }
    
    public String halfOfString(String message, int start){
        String halfString = "";
        for(int i = start; i < message.length(); i += 2){
            halfString = halfString + message.substring(i, i + 1);
        }
        return halfString;
    }
    
    public int getKey(String s){
       //count frequency of letters
       int[] freqs = countLetters(s);
       //find the index for max frequency
       int maxDex = maxIndex(freqs);
       //find the shift to the letter e
       int dkey = maxDex - 4;
       if(maxDex < 4){
           dkey = 26 - (4 - maxDex);
       }
       return dkey;
    }
    
    public void testDecrypt() {
        
        //testing encryptTwoKeys
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        String decrypted = decryptTwoKeys(encrypted);
        System.out.println(decrypted);
        System.out.println(encrypted);
        
    }
    
    public String decryptTwoKeys(String encrypted){
        //first half of encrypted
        String firstHalf = halfOfString(encrypted,0);
        //second half of encrypted
        String secondHalf = halfOfString(encrypted,1);
        //first half key
        int firstKey = getKey(firstHalf);
        //second half key
        int secondKey = getKey(secondHalf);
        //print keys
        System.out.println("The first key is " + firstKey);
        System.out.println("The second key is " + secondKey);
        //Your answer is the String inside of encrypted
        return encryptTwoKeys(encrypted,26-firstKey,26-secondKey);
    }


    public String encryptTwoKeys(String input, int key1, int key2){
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //Compute key1 shifted alphabet
        String key1ShiftedAlphabet = alphabet.substring(key1)+
        alphabet.substring(0,key1);
        //Computer key2 shifted alphabet
        String key2ShiftedAlphabet = alphabet.substring(key2)+alphabet.substring(0,key2);
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //make currChar uppercase
            char currCharUpper = Character.toUpperCase(currChar);
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(currCharUpper);
            //If currChar is in the alphabet
            if(idx != -1){
                //check if index is even
                if(i % 2 == 0){
                    //shift with key1
                    //Get the idxth character of shiftedAlphabet (newChar)
                    char newChar = key1ShiftedAlphabet.charAt(idx);
                    //check if currChar is uppercase
                    if(currChar == alphabet.charAt(idx)){
                        //Replace the ith character of encrypted with newChar
                        encrypted.setCharAt(i, newChar);
                    } else {
                        //otherwise replace the ith character of encrypted with lowercase newChar
                        encrypted.setCharAt(i, Character.toLowerCase(newChar));
                    }
                } else {
                    //shift with key2
                    //Get the idxth character of shiftedAlphabet (newChar)
                    char newChar = key2ShiftedAlphabet.charAt(idx);
                    //check if currChar is uppercase
                    if(currChar == alphabet.charAt(idx)){
                        //Replace the ith character of encrypted with newChar
                        encrypted.setCharAt(i, newChar);
                    } else {
                        //otherwise replace the ith character of encrypted with lowercase newChar
                        encrypted.setCharAt(i, Character.toLowerCase(newChar));
                    }
                }
            }
            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }
}
