
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class TestCaesarCipherTwo {
    //fields
    
    //constructor
    
    //methods
    //split method into two strings
    private String halfOfString(String message, int start){
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String halfString = "";
        for(int i = start; i < message.length(); i += 2){
            halfString = halfString + message.substring(i, i + 1);
        }
        return halfString;
    }
    //count frequency of letters in message
    private int[] countLetters(String input){
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //counting occurrences
        int[] counts = new int[26];
        for(int i = 0; i < input.length(); i++){
            char ch = Character.toUpperCase(input.charAt(i));
            int dex = alphabet.indexOf(ch);
            if(dex != -1){
                counts[dex] += 1;
            }
        }
        return counts;
    }
    //find the index of the highest frequency letter
    private int maxIndex(int[] values){
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
    //tests CaesarCipherTwo
    public void simpleTests(){
        //read in file
        FileResource fr = new FileResource();
        //convert file to string
        String message = fr.asString();
        //initialize new CaesarCipher object with keys 17 and 3
        CaesarCipherTwo cc = new CaesarCipherTwo(17,3);
        //encrypted message with key 18
        String encrypted = cc.encrypt(message);
        //output encryption
        System.out.println(encrypted);
        //decrypt the encrypted message
        String decrypted = cc.decrypt(encrypted);
        //output decrypted message
        System.out.println(decrypted);
        //break encryption without keys
        String broken = breakCaesarCipher(encrypted);
        //output decrypted message
        System.out.println(broken);
    }
    //break the CaesarCipherTwo by finding the keys
    public String breakCaesarCipher(String input){
       //first half of encrypted
       String firstHalf = halfOfString(input,0);
       //second half of encrypted
       String secondHalf = halfOfString(input,1);
       //count frequency of letters
       int[] freqs1 = countLetters(firstHalf);
       //find the index for max frequency
       int maxDex1 = maxIndex(freqs1);
       //find the shift to the letter e
       int dkey1 = maxDex1 - 4;
       if(maxDex1 < 4){
           dkey1 = 26 - (4 - maxDex1);
       }
       //count frequency of second half of letters
       int[] freqs2 = countLetters(secondHalf);
       //find the index for max frequency
       int maxDex2 = maxIndex(freqs2);
       //find the shift to the letter e
       int dkey2 = maxDex2 - 4;
       if(maxDex2 < 4){
           dkey2 = 26 - (4 - maxDex2);
       }
       //create new object with found keys
       CaesarCipherTwo cc = new CaesarCipherTwo(dkey1,dkey2);
       //decrypt message with found key
       String decrypted = cc.decrypt(input);
       //return decryption
       return decrypted;
    }
    
    
    
    

}
