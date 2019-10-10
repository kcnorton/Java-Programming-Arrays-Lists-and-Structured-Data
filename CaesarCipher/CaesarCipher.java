import edu.duke.*;

public class CaesarCipher {
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
    public void testCaesar() {
        int key = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
        
        //testing key 15
        int keyTest = 15;
        String messageTest = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        String encryptedTest = encrypt(messageTest,keyTest);
        System.out.println(encryptedTest);
        
        //testing encryptTwoKeys
        int key1 = 23;
        int key2 = 17;
        String input = "First Legion";
        String encryptTwo = encryptTwoKeys(input, key1, key2);
        System.out.println(encryptTwo);
        System.out.println(input);
        
        //testing key1 8 and key2 21
        int key1Test = 8;
        int key2Test = 21;
        String inputTest = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        String encryptTwoTest = encryptTwoKeys(inputTest, key1Test, key2Test);
        System.out.println(encryptTwoTest);
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

