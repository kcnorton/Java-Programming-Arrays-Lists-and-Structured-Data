
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class TestCaesarCipher {
    //fields
    
    //constructor
    
    //methods
    //counts frequencies of letters in a message
    private int[] countLetters(String input){
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
    //finds the index of the letter with the highest frequency
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
    //tests CaesarCiphers
    public void simpleTests(){
        //read in file
        FileResource fr = new FileResource();
        //convert file to string
        String message = fr.asString();
        //initialize new CaesarCipher object with key 18
        CaesarCipher cc = new CaesarCipher(18);
        //encrypted message with key 18
        String encrypted = cc.encrypt(message);
        //output encryption
        System.out.println(encrypted);
        //decrypt the encrypted message
        String decrypted = cc.decrypt(encrypted);
        //output decrypted message
        System.out.println(decrypted);
        //break encryption without a key
        String broken = breakCaesarCipher(encrypted);
        //output decrypted message
        System.out.println(broken);
    }
    //break the CaesarCipher by finding the key
    public String breakCaesarCipher(String input){
       //count frequency of letters
       int[] freqs = countLetters(input);
       //find the index for max frequency
       int maxDex = maxIndex(freqs);
       //find the shift to the letter e
       int dkey = maxDex - 4;
       if(maxDex < 4){
           dkey = 26 - (4 - maxDex);
       }
       //create new object with found key
       CaesarCipher cc = new CaesarCipher(dkey);
       //decrypt message with found key
       String decrypted = cc.decrypt(input);
       //return decryption
       return decrypted;
    }
}







