
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import java.io.*;

public class WordsInFiles {
    //fields
    private HashMap<String,ArrayList<String>> wordMap;
    
    //constructor
    public WordsInFiles(){
        wordMap = new HashMap<String,ArrayList<String>>();
        
    }
    //methods
    private void addWordsFromFile(File f){
        //add all words from f into wordMap
        //convert file to fileresource
        FileResource fr = new FileResource(f);
        //read each word in f
        for(String w : fr.words()){
            //check if word is in hashmap
            if(wordMap.containsKey(w)){
                //check arrayList for file name value
                if(!wordMap.get(w).contains(f.getName())){
                    //add filename to arrayList
                    wordMap.get(w).add(f.getName());
                }
            }
            else{
                //create new arrayList
                ArrayList<String> arraylist = new ArrayList<String>();
                //add filename to arrayList value
                arraylist.add(f.getName());
                //add key and value to wordMap
                wordMap.put(w,arraylist);
            }
        }
        return;
        
    }
    //
    public void buildWordFileMap(){
        //for each file put all of its words into the map
        wordMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    //
    public int maxNumber(){
        //return the max number of files a word appears in
        //initialize count
        int count = 0;
        //initialize max
        int max = 0;
        //initialize max key
        String maxWord = "";
        //iterate over keys
        for(String s : wordMap.keySet()){
            //get size of arraylist value
            count = wordMap.get(s).size();
            //check if count is larger than max
            if(count > max){
                //replace max with count
                max = count;
                maxWord = s;
            }
        }
        return max;
    }
    //
    public ArrayList wordsInNumFiles(int number){
        //return arraylist of words that appear in exactly number files
        //initialize arraylist
        ArrayList<String> words = new ArrayList<String>();
        for(String s : wordMap.keySet()){
            //get size of arraylist value
            int count = wordMap.get(s).size();
            //check that size equals number
            if(count == number){
                //add to arraylist
                words.add(s);
            }
        }
        return words;
    }
    //
    public void printFilesIn(String word){
        //print names of the files this word appears in
        ArrayList<String> files = new ArrayList<String>();
        files = wordMap.get(word);
        for(String s : files){
            System.out.println(s);
        }
    }
    //
    public void tester(){
        //
        buildWordFileMap();
        int max = maxNumber();
        System.out.println("The max number of files a word appears in is " + max);
        ArrayList<String> wordMaxFiles = new ArrayList<String>();
        wordMaxFiles = wordsInNumFiles(max);
        //for(String s : wordMaxFiles){
        //    System.out.println(s + " is in " + max + " number of files");
        //}
        int maxFiles = wordMaxFiles.size();
        System.out.println(maxFiles + " words are in " + max + " number of files");
        System.out.println(wordMap.get("red"));
    }
    
    
    
    
    
}
