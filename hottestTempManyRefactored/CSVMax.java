/**
 * Find the highest (hottest) temperature in any number of files of CSV weather data chosen by the user.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMax {
    public CSVRecord hottestHourInFile(CSVParser parser) {
        //start with largestSoFar as nothing
        CSVRecord largestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        //The largestSoFar is the answer
        return largestSoFar;
    }
    
    public CSVRecord coldestHourInFile(CSVParser parser){
        //start with smallestSoFar as nothing
        CSVRecord smallestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
        }
        //The smallestSoFar is the answer
        return smallestSoFar;
    }
    
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + smallest.get("TemperatureF") +
                   " at " + smallest.get("DateUTC"));
    }

    public void testHottestInDay () {
        FileResource fr = new FileResource("data/2015/weather-2015-01-01.csv");
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("hottest temperature was " + largest.get("TemperatureF") +
                   " at " + largest.get("TimeEST"));
    }

    public CSVRecord hottestInManyDays() {
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // use method to get largest in file.
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            // use method to compare two records
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        //The largestSoFar is the answer
        return largestSoFar;
    }
    
    public String fileWithColdestTemperature() {
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        String filename = "";
        // iterate over files
        for (File f : dr.selectedFiles()) {
            //get current filename
            String currFileName = f.getName();
            FileResource fr = new FileResource(f);
            // use method to get smallest in file.
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            // use method to compare two records
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
            if(smallestSoFar.equals(currentRow)){
                filename = currFileName;
            }
        }
        //The smallestSoFar is the answer
        return filename;
    }

    public CSVRecord getLargestOfTwo (CSVRecord currentRow, CSVRecord largestSoFar) {
        //If largestSoFar is nothing
        if (largestSoFar == null) {
            largestSoFar = currentRow;
        }
        //Otherwise
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
            //Check if currentRow’s temperature > largestSoFar’s
            if (currentTemp > largestTemp) {
                //If so update largestSoFar to currentRow
                largestSoFar = currentRow;
            }
        }
        return largestSoFar;
    }
    
    public CSVRecord getSmallestOfTwo (CSVRecord currentRow, CSVRecord smallestSoFar) {
        //If smallestSoFar is nothing
        if (smallestSoFar == null) {
            smallestSoFar = currentRow;
        }
        //Otherwise
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
            //Check if currentTemp == -9999
            if(currentTemp == -9999){
                //return smallestSoFar
                return smallestSoFar;
            }
            //Check if currentRow’s temperature < smallestSoFar’s
            if (currentTemp < smallestTemp) {
                //If so update smallestSoFar to currentRow
                smallestSoFar = currentRow;
            }
        }
        return smallestSoFar;
    }

    public void testHottestInManyDays () {
        CSVRecord largest = hottestInManyDays();
        System.out.println("hottest temperature was " + largest.get("TemperatureF") +
                   " at " + largest.get("DateUTC"));
    }
    
    public void testFileWithColdestTemperature(){
        String smallest = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + smallest);
        //get year from filename
        String year = smallest.substring(8,12);
        FileResource fr = new FileResource("data/" + year + "/" + smallest);
        CSVRecord smallestHour = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature on that day was " + smallestHour.get("TemperatureF") +
                   " at " + smallestHour.get("TimeEST"));
        //
        CSVParser parser = fr.getCSVParser();
        System.out.println("All the Temperatures on the coldest day were:");
        for (CSVRecord currentRow : parser) {
            //print info
            System.out.println(currentRow.get("TemperatureF") +
                   " at " + currentRow.get("DateUTC"));
        }
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        //return CSVRecord that has lowest humidity
        //start with lowestSoFar as nothing
        CSVRecord lowestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            lowestSoFar = getLowestOfTwo(currentRow, lowestSoFar);
        }
        //The lowestSoFar is the answer
        return lowestSoFar;
    }
    
    public CSVRecord getLowestOfTwo (CSVRecord currentRow, CSVRecord lowestSoFar) {
        //If lowestSoFar is nothing
        if (lowestSoFar == null) {
            lowestSoFar = currentRow;
        }
        //Otherwise
        else {
            //get currentHum as string
            String currHumString = currentRow.get("Humidity");
            //Check if currHumString is N/A
            if(currHumString.equals("N/A")){
                //return lowestSoFar
                return lowestSoFar;
            }
            double currentHum = Double.parseDouble(currentRow.get("Humidity"));
            double lowestHum = Double.parseDouble(lowestSoFar.get("Humidity"));
            //Check if currentRow’s humidity < lowestSoFar’s
            if (currentHum < lowestHum) {
                //If so update lowestSoFar to currentRow
                lowestSoFar = currentRow;
            }
        }
        return lowestSoFar;
    }
    
    public void testLowestHumidityInFile(){
        //
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // use method to get lowest in file.
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            // use method to compare two records
            lowestSoFar = getLowestOfTwo(currentRow, lowestSoFar);
        }
        //The lowestSoFar is the answer
        return lowestSoFar;
    }
    
    public void testLowestHumidityInManyFiles(){
        //
        CSVRecord lowest = lowestHumidityInManyFiles();
        System.out.println("Lowest humidity was " + lowest.get("Humidity") +
                   " at " + lowest.get("DateUTC"));
    }
    
    public double averageTemperatureInFile(CSVParser parser){
        //return double that represents average temperature in the file
        //initialize sum
        double sum = 0;
        //initialize count
        double count = 0;
        //intialize averageT
        double averageT = 0;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // add temperature to sum
            sum = sum + Double.parseDouble(currentRow.get("TemperatureF"));
            //increase count by 1
            count += 1;
        }
        //calculate average
        averageT = sum/count;
        return averageT;
    }
    
    public void testAverageTemperatureInFile(){
        //
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        Double averageT = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + averageT);
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        //return average temperature of only those temperatures when the humidity is greater than or equal to value
        //initialize sum
        double sum = 0;
        //initialize count
        double count = 0;
        //intialize averageT
        double averageT = 0;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            //Check if humidity is greater than or equal to value
            if(Double.parseDouble(currentRow.get("Humidity")) >= value){
                // add temperature to sum
                sum = sum + Double.parseDouble(currentRow.get("TemperatureF"));
                //increase count by 1
                count += 1;
            }
        }
        //calculate average
        averageT = sum/count;
        return averageT;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        //
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double averageT = averageTemperatureWithHighHumidityInFile(parser,80);
        //check if averageT is greater than 0
        if(!(averageT > 0)){
            System.out.println("No temperature with that humidity");
        }
        else{
            System.out.println("Average temperature when high humidity is " + averageT);
        }
    }
    
    
    
    
    
    
    
    
}
