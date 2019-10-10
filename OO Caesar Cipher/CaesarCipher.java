
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class CaesarCipher {
    //fields
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    
    //constructor
    public CaesarCipher(int key){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        mainKey = key;
    }
    
    //methods
    public String encrypt(String input){
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
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
    
    public String decrypt(String input){
        //create new object with decryption key
        CaesarCipher cc = new CaesarCipher(26-mainKey);
        //return the encrypted string using the decryption key
        return cc.encrypt(input);
    }
}





