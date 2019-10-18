
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;

public class Tester {
    //
    public void testCaesarCipher(){
        //create new CaesarCipher object
        CaesarCipher cc = new CaesarCipher(5);
        String encryption = cc.encrypt("titus-small.txt");
        String decryption = cc.decrypt(encryption);
        System.out.println("Encryption is\t" + encryption);
        System.out.println("Decryption is\t" + decryption);
    }
    
    public void testCaesarCracker(){
        //create new CaesarCracker object
        CaesarCracker ccr = new CaesarCracker();
        String decryption = ccr.decrypt("titus-small_key5.txt");
        System.out.println("Decryption is\t" + decryption);
    }
    
    public void testVigenereCipher(){
        int[] rome = {17,14,12,4};
        //create VigenereCipher object
        VigenereCipher vc = new VigenereCipher(rome);
        //encrypt 
        String encryption = vc.encrypt("titus-small.text");
        //decrypt
        String decryption = vc.decrypt(encryption);
        //print
        System.out.println("Encryption is\t" + encryption);
        System.out.println("Decryption is\t" + decryption);
    }
    
    public void testVigenereBreaker(){
        //create VigenereBreaker object
        VigenereBreaker vb = new VigenereBreaker();
        //String slice = vb.sliceString("abcdefghijklm",0,3);
        //System.out.println("The sliced string is " + slice);
        //int [] key = vb.tryKeyLength("athens_keyflute.txt",5,'e');
        //System.out.println("The key is " + Arrays.toString(key));
        vb.breakVigenere();
    }
    
    public void testCountWords(){
        //
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        VigenereBreaker vb = new VigenereBreaker();
        FileResource d = new FileResource();
        HashSet dictionary = vb.readDictionary(d);
        int[] key = vb.tryKeyLength(encrypted,5,'e');
        VigenereCipher vc = new VigenereCipher(key);
        //decrypt
        //System.out.println(encrypted);
        String decryption = vc.decrypt(encrypted);
        //System.out.println(decryption);
        //count how many Real words
        int count = vb.countWords(decryption,dictionary);
        System.out.println("count is " + count);
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
