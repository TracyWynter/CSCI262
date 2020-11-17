// Package
package main;

// Import Libraries
// For Variables
import java.util.Arrays;    // Arrays
import java.util.ArrayList; // ArrayList

// Read from file
import main.stats;
import main.events;
import java.io.File;
import java.io.IOException; // IO Exception
import java.util.Scanner;   // To use Scanner class to read in data from files

public class Input 
{
    // ---------- MAIN METHODS -----------------
    // initial input 
    public void startInput(String eventsFile, String statsFile, ArrayList<events> eventList, ArrayList<stats> statList) 
    {
        // Extract information from both files and store them 
        readEventFile(eventsFile, eventList); // Read from Events.txt
        readStatFile(statsFile, statList);    // Read from Stats.txt
        validateData(eventList, statList);    // validate that data in both ArrayList are consistent
    }

    // -------------- SUB METHODS --------------
    // Method reads in information from Events.txt file
    public void readEventFile(String eventFile, ArrayList<events> eventList) 
    {
        System.out.println("\nReading the " + eventFile + " ...");
        try 
        {
            File fileObj = new File(eventFile); // Create new File opbject
            Scanner sct = new Scanner(fileObj); // Create new Scanner object

            String numberOfEvents = sct.nextLine();             //Reading the first line of the file
            int eventCount = Integer.valueOf(numberOfEvents);   // Convert to Integer
            if (eventCount > 0) 
            {
                int n = 0; // Counter to check number or records
                while (sct.hasNextLine()) 
                {
                    String eventText = sct.nextLine();          // Takes in next line
                    String[] eventLine = eventText.split(":");  // Delimit 

                    // Add to ArrayLists
                    String eventName = (eventLine[0]);          // Stores event name
                    char eventType = (eventLine[1]).charAt(0);  // Stores event type
                    double minimum = 0.0;                       // Stores minimum value
                    double maximum = 0.0;                       // Stores maximum value
                    Integer weight = 0;                         // Stores Weight

                    if (eventLine[1].equals("D")) 
                    { // Discrete events
                        int min = 0;
                        try 
                        {// Check that discrete events [D] values are all itegers - events.txt 
                            min = Integer.parseInt(eventLine[2]);    // Check if can parse as integer
                            minimum = (Double.valueOf(eventLine[2]));// Stores minimum value
                        } 
                        catch (NumberFormatException e) 
                        {
                            min = 0;
                            minimum = 0.0;
                        }

                        int max = 0;
                        try 
                        { //Test for blank spacing
                            max = Integer.valueOf(eventLine[3]);
                            maximum = Double.valueOf(eventLine[3]);
                        } 
                        catch (NumberFormatException ex) 
                        {
                            max = 0;
                            maximum = 0.0;
                        }

                        if (max != 0  && min > max) // Minimum needs to be smaller than maximum
                        {
                            System.out.println("Error! Minimum value cannot be larger than Maximum value");
                            System.exit(0);
                        }
                    } 
                    else if (eventLine[1].equals("C")) 
                    {// Continuous events
                        minimum = Double.valueOf(eventLine[2]);           // Stores minimum value
                        try {maximum = Double.valueOf(eventLine[3]);}     // Test for blank  
                        catch (NumberFormatException ex) {maximum = 0.0;}
                    }

                    if (Integer.parseInt(eventLine[4]) > 0) {weight = Integer.valueOf(eventLine[4]);}
                    else 
                    {
                        System.out.println("Error! Weight cannot be negative!");
                        System.exit(0);
                    }
                    n++;
                    // Store the data for processing at next step
                    eventList.add(new events(eventName, eventType, minimum, maximum, weight));
                }
                sct.close();

                if (n == eventCount) // [3] Check number of events against first digit in the file.
                {System.out.println("Successfully read in " + eventFile + "!");}// If it matches
                else 
                {// If it does not match
                    System.out.println("Failed to read in " + eventFile + "!");
                    System.out.println("Number of records specified does not match the number of records listed!");
                    System.out.println("Program will terminate!");
                    System.exit(0);
                }
            } 
            else 
            {
                System.out.println("Error! Number of events cannot be negative!");
                System.exit(0);
            }

        } 
        catch (IOException e) {System.err.println(e); System.exit(0);}   
        
        // Displaying contents of data read in to user
        System.out.println("Contents of " + eventFile + " read in:");
        String temp = String.format("%-15s", "Event name") + ":";
        temp = temp + String.format("%-4s", "[CD]") + ":";
        temp = temp + String.format("%-7s", "minimum") + ":";
        temp = temp + String.format("%-7s", "maximum") + ":";
        temp = temp + String.format("%-6s", "weight") + ":";
        System.out.println(temp);
        for (events e : eventList) {System.out.println(e);}
    }

    // Method reads in information from Stats.txt file
    public void readStatFile(String statsFile, ArrayList<stats> statList) 
    {
        System.out.println("\nReading the " + statsFile + " ...");
        try 
        {
            File fileObj = new File(statsFile); // Create new File opbject
            Scanner sct = new Scanner(fileObj); // Create new Scanner object

            String numberOfEvents = sct.nextLine();           //Reading the first line of the file
            int eventCount = Integer.valueOf(numberOfEvents); // Convert to Integer

            int n = 0; // Counter to check number or records
            while (sct.hasNextLine()) 
            {
                String eventText = sct.nextLine();          // Takes in next line
                String[] statsLine = eventText.split(":");  // Delimit 

                String eventName = statsLine[0];            // Stores Event name
                double mean = Double.valueOf(statsLine[1]); // Stores Mean value
                double sd = Double.valueOf(statsLine[2]);   // Stores Standard Deviation
                n++;
                // Store the data for processing at next step
                statList.add(new stats(eventName, mean, sd));
            }
            sct.close();

            if (n == eventCount) // [3] Check number of events against first digit in the file.
            {System.out.println("Successfully read in " + statsFile + "!");} // If it matches              
            else 
            {// If it does not match
                System.out.println("Failed to read in " + statsFile + "!");
                System.out.println("Number of records specified does not match the number of records listed!");
                System.out.println("Program will terminate!");
                System.exit(0);
            }
        } 
        catch (IOException e) {System.err.println(e); System.exit(0);} 
        
        // Displaying contents of data read in to user
        System.out.println("Contents of " + statsFile + " read in:");
        String temp = String.format("%-15s", "Event name") + ":";
        temp = temp + String.format("%-7s", "mean") + ":";
        temp = temp + String.format("%-18s", "standard deviation") + ":";
        System.out.println(temp);
        for (stats s : statList) {System.out.println(s);}
    }

    // Validate all parameters supplied in as arguments when running the IDS program
    public boolean validateInput(String[] args) 
    {
        boolean[] valid = new boolean[4];
        Arrays.fill(valid, false);
        
        if (args[0].equals("IDS"))        {valid[0] = true;} // check 1st arg
        if (args[1].equals("Events.txt")) {valid[1] = true;} // check 2nd arg  
        if (args[2].equals("Stats.txt"))  {valid[2] = true;} // check 3rd arg
        
        try // check 4th arg (must be integer)
        {
            int num = Integer.valueOf(args[3]);
            if (num > 0) {valid[3] = true;} // must be a positive integer
        } 
        catch (NumberFormatException ex) {valid[3] = false;}

        if (!Arrays.asList(valid).contains(false)) {return true;}
        else {return false;}
    }

    public void validateData(ArrayList<events> eventList, ArrayList<stats> statList) 
    {
        System.out.println("Validating data which has been read in ...");
        // Check number of records in events.txt and stats.txt are the same
        // In order to achieve this we will compare the size of both ArrayList.
        Integer eventListSize = eventList.size(); // Get size of eventList
        Integer statListSize = statList.size();   // Get size of statList

        // Check if they are equal
        System.out.println("Checking dataset size in both files...");
        if (eventListSize == statListSize)
        {System.out.println("Size of Datasets in both files match");}
        else
        {
            System.out.println("Data sets in both Events.txt and Stats.txt are not the same size!");
            System.out.println("Ensure that both datasets are consistent!\nProgram Terminating!");
            System.exit(0);
        }
        // Event names in both files are the same and in the same order.
    }
}
