package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;   // To use Scanner class to read in data from files
import main.stats;

public class AlertEngine {
    // alert engine 
    //To check consitency between "live data" and the base line statistics

    //Method for getting the new filename
    public String getUserInputNewStatFile() {

        Scanner sct = new Scanner(System.in); // Create new Scanner object

        // Prompt user for file that contains new statistics
        //Same format as Stats.txt but different parameters
        System.out.println("Please enter the filename: ");

        //Reading user input
        String newStatsFile = sct.nextLine();
        return newStatsFile;
    }

    //Method for getting the number of days from user
    public Integer getUserInputNumOfDays() {
        Scanner sct = new Scanner(System.in); // Create new Scanner object

        String numOfDays;
        int noOfDays = 0;
        System.out.println();

        //Prompt user to enter number of days
        System.out.println("\nPlease enter the number of days you would like to generate: ");

        //Reading numOfDays input as String
        numOfDays = sct.nextLine();
        //Converting user input to integer
        noOfDays = Integer.parseInt(numOfDays);
        return noOfDays;
    }

    //Method to read in new statistics file
    public void readNewStatFile(ArrayList<stats> newStatList) {
        try {
            //Calling getUserInputNewStatFile to get the file that contains new statistics
            String newStatsFile = getUserInputNewStatFile();
            //int noOfDays = getUserInputNumOfDays();

            File fileObj = new File(newStatsFile); // Create new File opbject
            Scanner sct = new Scanner(fileObj); // Create new Scanner object

            String numberOfEvents = sct.nextLine(); //Reading the first line of the file
            int eventCount = Integer.valueOf(numberOfEvents); // Convert to Integer

            int n = 0; // Counter to check number or records
            while (sct.hasNextLine()) {
                String eventText = sct.nextLine(); // Takes in next line
                String[] statsLine = eventText.split(":"); // Delimit 

                String eventName = statsLine[0]; // Stores Event name
                double mean = Double.valueOf(statsLine[1]); // Stores Mean value
                double sd = Double.valueOf(statsLine[2]); // Stores Standard Deviation
                n++;
                //Storing the data for processing at the next step
                newStatList.add(new stats(eventName, mean, sd));
            }
            sct.close();

            if (n == eventCount) // [3] Check number of events against first digit in the file.
            {// If it matches
                System.out.println("Successfully read in " + newStatsFile + "!");
            } else {// If it does not match
                System.out.println("Failed to read in " + newStatsFile + "!");
                System.out.println("Number of records specified does not match the number of records listed!");
                System.out.println("Program will terminate!");
                System.exit(0);
            }
        } catch (IOException e) {
            System.err.println(e);
            System.exit(0);
        }

        // Debug Code
        //Printing data of newStatList	
        for (stats s : newStatList) {
            System.out.println(s);
        }
    }

    public void alertEngine(ArrayList<stats> newStatList) {
        // Read and store data
        readNewStatFile(newStatList);

        // Run activity engine and produce data for the number of days specified
        // Run analysis engine to produce daily totals
    }
}
