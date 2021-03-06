// Package
//package main;

// Import Libraries
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;   // To use Scanner class to read in data from files
import main.stats;
import java.math.RoundingMode;  // For rounding off
import java.text.DecimalFormat; // For rounding off

public class AlertEngine 
{
    // alert engine 
    //To check consitency between "live data" and the base line statistics

    // Class Object
    Input ipt = new Input();
    SimulateEngine sim = new SimulateEngine();
    analyseEngine analysis = new analyseEngine();
    //AlertEngine alert = new AlertEngine();

    // Custom File Objects
    //ArrayList<events> eventList = new ArrayList<>();
    //ArrayList<stats> statList = new ArrayList<>();
    ArrayList<stats> newStatList = new ArrayList<>();
    ArrayList<events> eventList = new ArrayList<>();

    // Used to hold new list of generated logs based on new stat file
    ArrayList<String> newLogList = new ArrayList<>();

    ArrayList<ArrayList<logs>> logDataList = new ArrayList<>();
    //ArrayList<logs> rawlog = new ArrayList<logs>();

    //Method for getting the new filename
    public String getUserInputNewStatFile() {

        Scanner sct = new Scanner(System.in); // Create new Scanner object

        // Prompt user for file that contains new statistics
        //Same format as Stats.txt but different parameters
        System.out.print("Please enter the filename: ");

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
        System.out.print("\nPlease enter the number of days you would like to generate: ");

        //Reading numOfDays input as String
        numOfDays = sct.nextLine();
        //Converting user input to integer
        noOfDays = Integer.parseInt(numOfDays);
        return noOfDays;
    }

    //Method to read in new statistics file
    public void readNewStatFile(ArrayList<stats> newStatList) 
    {
        String newStatsFile = "";
        boolean fileExists = false;
        while (fileExists == false) // Validate that new Stats file name exists
        {
            //Calling getUserInputNewStatFile to get the file that contains new statistics
            newStatsFile = getUserInputNewStatFile();

            System.out.println("Checking if files exists...");
            File tempFile = new File(newStatsFile);
            fileExists = tempFile.exists();
            if (fileExists == true) {
                System.out.println("File exists! Proceesing to read file...\n");
            } else {
                System.out.println("File does not exist! Please key in another file name!\n");
            }
        }

        try {
            //int noOfDays = getUserInputNumOfDays();

            File fileObj = new File(newStatsFile); // Create new File opbject
            Scanner sct = new Scanner(fileObj); // Create new Scanner object

            String numberOfEvents = sct.nextLine(); //Reading the first line of the file
            int eventCount = Integer.valueOf(numberOfEvents); // Convert to Integer
            if (eventCount > 0) {
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
            } else {
                System.out.println("Error! Number of events cannot be negative!");
                System.exit(0);
            }
        } catch (IOException e) {
            System.err.println(e);
            System.exit(0);
        }

        // Debug Code
        //Printing data of newStatList
        // Displaying contents of data read in to user
        System.out.println("Contents of " + newStatsFile + " read in:");
        String temp = String.format("%-15s", "Event name") + ":";
        temp = temp + String.format("%-7s", "mean") + ":";
        temp = temp + String.format("%-18s", "standard deviation") + ":";
        for (stats s : newStatList) {
            //System.out.println("Printing data of new statistics file...");
            System.out.println(s);
        }
    }

    public String getUserOptions() {
        System.out.print("\nWould you like to run the Alert Engine(Y or N): ");
        Scanner sct = new Scanner(System.in); // Create new Scanner object
        String optionsToContinue = sct.nextLine(); // Takes in next line
        String userOption = optionsToContinue.toUpperCase();
        return userOption;
    }

    public int getThreshold(ArrayList<events> eventList) {
        int Weight = 0;
        for (int i = 0; i < eventList.size(); i++) 
        {
            //Retrieving weight from events.txt
            Weight += eventList.get(i).getWeight();
            //System.out.println(Weight);
        }
        return Weight;
    }

    //(ABS((BaselineStat_Mean - log.txt(value)) / BaselineStat_standardDev)*weight(for each event))
    public void compareThreshold(ArrayList<events> eventList, ArrayList<stats> baseLineStats) 
    {
        int threshold = getThreshold(eventList);
        double totalThreshold = 2 * threshold;
        
        //Daily Counter
        //Compare with totalThreshold & prompt if over totalThreshold 
        //readInLogFile();
        logDataList = analysis.readInLogFile("newLog.txt");
        ArrayList<stats> bls = analysis.calculateBaselineStats(logDataList); //base line stats
        ArrayList<ArrayList<logs>> totals = analysis.calculateTotals(eventList, logDataList, bls);


        System.out.println("\n\nTotal Threshold: " + totalThreshold);
        ArrayList<Double> dailyCounter = new ArrayList<>();

        for (int k = 0; k < totals.size(); k++)
        {
            double sum = 0.0;
            int tempo = totals.get(0).size();
            for (int j = 1; j < tempo; j++)
            {
                sum = sum + totals.get(k).get(j).getValue();
            }
            dailyCounter.add(sum);
        } 
        
        for (int t = 0; t < dailyCounter.size(); t++)
        {
            // Rounding off mean to 2 Decimal Places.
            DecimalFormat formatDecimal = new DecimalFormat("0.00");
            // Format decimal outputs a string, so we need to convert it
            String temp = formatDecimal.format(dailyCounter.get(t));
            System.out.println("Day " + (t + 1));
            if (dailyCounter.get(t) >=  totalThreshold)
            {
                System.out.println("Suspicious Activity Detected!");
                System.out.println("Daily total: " + temp);
            }
            else
            {
                System.out.println("All normal...Nothing to report");
                System.out.println("Daily total: " + temp);
            }
            System.out.println("");
        }        
    }
    public void alertEngine(ArrayList<stats> newStatList, ArrayList<events> eventList, ArrayList<stats> baseLineStats) 
    {
        String optionsToContinue = getUserOptions();;

        if (optionsToContinue.equals("Y")) 
        {
            // Read and store data
            readNewStatFile(newStatList);

            // Run activity engine and produce data for the number of days specified
            int noOfDays = getUserInputNumOfDays();
            sim.generateEvents(noOfDays, newStatList, eventList, newLogList);
            sim.logEvents(newLogList, "newLog.txt");

            // Debug Code
            //for (String s : newLogList)
            //{System.out.println(s);}
            // Run analysis engine to produce daily totals
            // analysis.analyseEngine(days);
            compareThreshold(eventList, baseLineStats);

        }
         else if (optionsToContinue.equals("N")) 
        {
            System.out.println("Exiting... ");
            System.exit(0);
        } 
        else 
        {
            System.out.println("Error! Invalid Options!");
            alertEngine(newStatList, eventList, baseLineStats);
        }
    }
}
