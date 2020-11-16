// Package
//package main;

// Import Libraries
// For Variables
import java.util.Arrays;  // Arrays
import java.util.ArrayList; // ArrayList

// Read from file
import java.io.File;
import java.io.IOException; // IO Exception
import java.util.Scanner;   // To use Scanner class to read in data from files

public class Input {

    // To store data from Events.txt
    //ArrayList<String> eventList = new ArrayList<>();
    //ArrayList<String> cdEventList = new ArrayList<>();
    //ArrayList<Integer> minimumList = new ArrayList<>();
    //ArrayList<Integer> maximumList = new ArrayList<>();
    //ArrayList<Integer> weightList = new ArrayList<>();
    ArrayList<events> eventList = new ArrayList<>();

    // To store date from Stats.txt
    //ArrayList<String> statsEventList = new ArrayList<>();
    //ArrayList<Double> meanList = new ArrayList<>();
    //ArrayList<Double> sdList = new ArrayList<>();
    ArrayList<stats> statList = new ArrayList<>();


    // ---------- MAIN METHODS -----------------
    // initial input 
    public void startInput(String eventsFile, String statsFile) {
        // Extract information from both files and store them 
        readEventFile(eventsFile); // Read from Events.txt
        readStatFile(statsFile);   // Read from Stats.txt

    }

    // -------------- SUB METHODS --------------
    // Method reads in information from Events.txt file
    public void readEventFile(String eventFile) 
    {
        try 
        {
            File fileObj = new File(eventFile); // Create new File opbject
            Scanner sct = new Scanner(fileObj); // Create new Scanner object

            String numberOfEvents = sct.nextLine(); //Reading the first line of the file
            int eventCount = Integer.valueOf(numberOfEvents); // Convert to Integer
			if(eventCount>0){
				int n = 0; // Counter to check number or records
				while (sct.hasNextLine()) 
				{
					String eventText = sct.nextLine(); // Takes in next line
					String[] eventLine = eventText.split(":"); // Delimit 

					// Add to ArrayLists
					String eventName = (eventLine[0]);    // Stores event name
					char eventType = (eventLine[1]).charAt(0);  // Stores event type
					double minimum = 0.0; // Stores minimum value
					double maximum = 0.0; // Stores maximum value
					Integer weight = 0;  // Stores Weight

					if (eventLine[1].equals("D")) 
					{ // Discrete events
						int min = 0;
						try
						{ 
							min = Integer.parseInt(eventLine[2]); // Check if can parse as integer
							minimum = (Double.valueOf(eventLine[2]));// Stores minimum value
						} 
						catch (NumberFormatException e) 
						{
							System.out.println("Error! Minimum value is not an integer");
							System.exit(0);
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

						if (min > max) 
						{
							System.out.println("Error! Minimum value cannot be larger than Maximum value");
							System.exit(0);
						}
					} 
					else if (eventLine[1].equals("C")) 
					{// Continuous events
						minimum = Double.valueOf(eventLine[2]); // Stores minimum value
						try 
						{ // Test for blank
							maximum = Double.valueOf(eventLine[3]);
						} 
						catch (NumberFormatException ex) 
						{
							maximum = 0.0;
						}
					}

					if (Integer.parseInt(eventLine[4]) > 0) 
					{
						weight = Integer.valueOf(eventLine[4]);
					} 
					else 
					{
						System.out.println("Error! Weight cannot be negative!");
					}
					n++;
					eventList.add(new events(eventName, eventType, minimum, maximum, weight));
				}
				sct.close();
				
				if (n == eventCount) // [3] Check number of events against first digit in the file.
				{// If it matches
					System.out.println("Successfully read in " + eventFile + "!");
				} else {// If it does not match
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

        } catch (IOException e) {
            System.err.println(e);
            System.exit(0);
        }

        // Debug code
        /*
        for (events e : eventList)
        {
            System.out.println(e);
        }*/
    }

    public void readStatFile(String statsFile) 
    {
        try 
        {
            File fileObj = new File(statsFile); // Create new File opbject
            Scanner sct = new Scanner(fileObj); // Create new Scanner object

            String numberOfEvents = sct.nextLine(); //Reading the first line of the file
            int eventCount = Integer.valueOf(numberOfEvents); // Convert to Integer

            int n = 0; // Counter to check number or records
            while (sct.hasNextLine()) 
            {
                String eventText = sct.nextLine(); // Takes in next line
                String[] statsLine = eventText.split(":"); // Delimit 

                String eventName = statsLine[0]; // Stores Event name
                double mean = Double.valueOf(statsLine[1]); // Stores Mean value
                double sd = Double.valueOf(statsLine[2]); // Stores Standard Deviation
                n++;
                statList.add(new stats(eventName, mean, sd));
            }
            sct.close();

            if (n == eventCount) // [3] Check number of events against first digit in the file.
            {// If it matches
                System.out.println("Successfully read in " + statsFile + "!");
            } 
            else 
            {// If it does not match
                System.out.println("Failed to read in " + statsFile + "!");
                System.out.println("Number of records specified does not match the number of records listed!");
                System.out.println("Program will terminate!");
                System.exit(0);
            }
        }
        catch (IOException e) 
        {
            System.err.println(e);
            System.exit(0);
        }

        // Debug Code
        /*
        for (stats s : statList)
        {
            System.out.println(s);
        }*/
    }

    public boolean validateInput(String[] args) {
        boolean[] valid = new boolean[4];
        Arrays.fill(valid, false);

        // check 1st arg
        if (args[0].equals("IDS")) {
            valid[0] = true;
        }

        // check 2nd arg
        if (args[1].equals("Events.txt")) {
            valid[1] = true;
        }

        // check 3rd arg
        if (args[2].equals("Stats.txt")) {
            valid[2] = true;
        }

        // check 4th arg (must be integer)
        try {
            int num = Integer.parseInt(args[3]);
            if (num > 0) { // must be a positive integer
                valid[3] = true;
            }
        } catch (NumberFormatException ex) {
            valid[3] = false;
        }

        if (!Arrays.asList(valid).contains(false)) {
            return true;
        } else {
            return false;
        }
    }
}