
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class CaesarCipherTwo {
    //fields
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    
    //constructor
    public CaesarCipherTwo(int key1, int key2){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }
    
    //methods
    //encrypt message with two keys
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
                //check if index is even
                if(i % 2 == 0){
                    //shift with key1
                    //Get the idxth character of shiftedAlphabet (newChar)
                    char newChar = shiftedAlphabet1.charAt(idx);
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
                    char newChar = shiftedAlphabet2.charAt(idx);
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
    //decrypt message with two keys
    public String decrypt(String input){
        //create new object with decryption key
        CaesarCipherTwo cc = new CaesarCipherTwo(26-mainKey1,26-mainKey2);
        //return the encrypted string using the decryption key
        return cc.encrypt(input);
    }
    
   
    
    
    
    
    
}