
import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String,ArrayList<String>> myMap;
    private ArrayList<String> usedList;
    private ArrayList<String> usedCategoryList;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        myMap = new HashMap<String,ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        usedList = new ArrayList<String>();
        usedCategoryList = new ArrayList<String>();
    }
    
    public GladLibMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String[] labels = {"adjective","noun","color","country","name","animal","timeframe","verb","fruit"};
        for(String s : labels){
            ArrayList<String> words = new ArrayList<String>();
            words = readIt(source+"/"+s+".txt");
            myMap.put(s,words);
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        return randomFrom(myMap.get(label));
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        //check if substring is in category arraylist
        if(!usedCategoryList.contains(w.substring(first+1,last))){
            //add category to used list
            usedCategoryList.add(w.substring(first+1,last));
        }
        String sub = getSubstitute(w.substring(first+1,last));
        //check size of usedList
        if(usedList.size() == 0){
            usedList.add(sub);
            return prefix+sub+suffix;
        }
        //check if sub is in usedList
        while(true){
            if (! usedList.contains(sub)) {
                usedList.add(sub);
                break;   	     
            }
            sub = getSubstitute(w.substring(first+1,last));
        }
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public void makeStory(){
        usedList.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println(usedList.size());
        int number = totalWordsInMap();
        System.out.println("Total number of words is " + number);
        int total = totalWordsConsidered();
        System.out.println("Total number of categories used is " + total);
    }
    
    public int totalWordsInMap(){
        //returns total number of words in all arraylists in myMap
        //initialize size
        int size = 0;
        for(String s : myMap.keySet()){
            size = size + myMap.get(s).size();
        }
        return size;
    }
    
    public int totalWordsConsidered(){
        //returns total number of words in arraylist categories that were used
        //initialize size
        int size = 0;
        //find key from usedCategoryList and get size of value
        for(String s : myMap.keySet()){
            if(usedCategoryList.contains(s)){
                size = size + myMap.get(s).size();
            }
        }
        return size;
    }
    
    

    
    
    
    

}