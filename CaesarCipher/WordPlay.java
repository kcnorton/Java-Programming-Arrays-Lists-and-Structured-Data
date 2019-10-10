
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class WordPlay {
    
    public boolean isVowel(char ch){
        //check ch is alphabetical
        if(Character.isLetter(ch)){
            //make ch lowercase
            char chLower = Character.toLowerCase(ch);
            //check if chLower is a vowel
            if(chLower == 'a' || chLower == 'e' || chLower == 'i' || chLower == 'o' || chLower == 'u'){
                //return true if ch is a vowel
                return true;
            }
        }
        //otherwise return false
        return false;
    }
    
    public void tester(){
        //testing uppercase
        boolean res1 = isVowel('O');
        System.out.println("O is a vowel and therefore isVowel is " + res1);
        
        //testing lowercase
        boolean res2 = isVowel('a');
        System.out.println("a is a vowel and therefore isVowel is " + res2);
        
        //testing non-vowel
        boolean res3 = isVowel('F');
        System.out.println("F is not a vowel and therefore isVowel is " + res3);
        
        //testing symbol
        boolean res4 = isVowel('#');
        System.out.println("# is not a vowel and therefore isVowel is " + res4);
        
        //testing replaceVowels
        String res5 = replaceVowels("Hello World Out There", '*');
        System.out.println("Hellow World Out There is now " + res5);
        
        //testing emphasize
        String res6 = emphasize("Mary Bella Abracadabra", 'a');
        System.out.println("Mary Bella Abracadabra emphasized with a is " + res6);
    }
    
    public String replaceVowels(String phrase, char ch){
        //create new StringBuilder
        StringBuilder sb = new StringBuilder(phrase);
        //iterate through string
        int i;
        for(i = 0; i < sb.length(); i++){
            //check is character isVowel
            if(isVowel(sb.charAt(i))){
                sb.setCharAt(i, ch);
            }
        }
        return sb.toString();
    }
    
    public String emphasize(String phrase, char ch){
        //create new StringBuilder
        StringBuilder sb = new StringBuilder(phrase);
        //iterate through string
        int i;
        for(i = 0; i < sb.length(); i++){
            //store current character
            char currChar = sb.charAt(i);
            //check is character is equal to ch
            if((Character.toLowerCase(currChar) == ch) || (Character.toUpperCase(currChar) == ch)){
                //check ch location for even or odd
                if(i % 2 == 0){
                    //if location is odd and index is even replace ch with *
                    sb.setCharAt(i, '*');
                } else {
                    //else replace with +
                    sb.setCharAt(i, '+');
                }
            }
        }
        return sb.toString();
    }
}
