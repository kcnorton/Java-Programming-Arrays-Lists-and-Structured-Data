
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        //create LogAnalyzer object
        LogAnalyzer la = new LogAnalyzer();
        //call readFile on data in file
        la.readFile("short-test_log");
        //call printAll
        la.printAll();
    }
    
    public void testUniqueIP(){
        //
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        int unique = la.countUniqueIPs();
        System.out.println("The number of unique IP addresses is " + unique);
    }
    
    public void testPrintAllHigherThanNum(){
        //
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisitsOnDay(){
        //
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        ArrayList<String> uniqueIPs = la.uniqueIPVisitsOnDay("Mar 24");
        System.out.println(uniqueIPs.size());
    }
    
    public void testCountUniqueIPsInRange(){
        //
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        int num = la.countUniqueIPsInRange(200,299);
        System.out.println("Number of unique IPs in range are " + num);
    }
    
    public void testMostNumberVisitsByIP(){
        //
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        int num = la.countUniqueIPsInRange(200,299);
        System.out.println("Number of unique IPs in range are " + num);
    }
    
    public void testCountingWebsiteVisits(){
        //countVisitsPerIP
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        HashMap<String,Integer> ipVisits = la.countVisitsPerIP();
        System.out.println("countVisitsPerIP\t" + ipVisits);
        
        //mostNumberVisitsByIP
        int max = la.mostNumberVisitsByIP(ipVisits);
        System.out.println("mostNumberVisitsByIP\t" + max);
        
        //iPsMostVisits
        ArrayList<String> ipsMostVisits = la.iPsMostVisits(ipVisits);
        System.out.println("iPsMostVisits\t" + ipsMostVisits);
        
        //iPsForDays
        HashMap<String,ArrayList<String>> ipDays = la.iPsForDays();
        System.out.println("iPsForDays\t" + ipDays);
        
        //dayWithMostIpVisits
        String day = la.dayWithMostIPVisits(ipDays);
        System.out.println("dayWithMostIPVisits\t" + day);
        
        //iPsWithMostVisitsOnDay
        ArrayList<String> ipsWithMostOnDay = la.iPsWithMostVisitsOnDay(ipDays,"Mar 17");
        System.out.println("iPsWithMostVisitsOnDay\t" + ipsWithMostOnDay);
        
    }
    
    
    
    
    
}
