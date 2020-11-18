// Package
package main;

// Import Libraries
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Math.abs;
import static java.lang.Math.pow;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import main.logs;
import java.math.RoundingMode;  // For rounding off
import java.text.DecimalFormat; // For rounding off
import java.lang.Math;          // for using Absolute(abs), power (pow), squareroot (sqrt)

public class analyseEngine 
{
    // Variables
    // Used to hold all the data from log.txt
    private ArrayList<ArrayList<logs>> logList2 = new ArrayList<ArrayList<logs>>();

    // Used to hold all the mean and Standard Deviation for each event
    private ArrayList<stats> baseLineStats = new ArrayList<stats>();

    // Used to hold all the total values  
    private ArrayList<ArrayList<logs>> totalList = new ArrayList<ArrayList<logs>>();

    // Main Constuctor
    public analyseEngine() {}

    public void start(ArrayList<events> eventList)
    {
        System.out.println("\nBeginning Analysis...");
        // Reads in the log.txt and generates a 2D ArrayList of "log" objects
        readInLogFile();

        // Can only execute when a 2D arraylist of "log" objects are supplied in. (e.g. logList2)
        calculateBaselineStats();  
        
        // Can only execute when 2D arraylist of "log" objects are supplied
        // with an arraylist of baseLine Stats.
        calculateTotals(eventList);

        // Method will take in the totalList ArrayList and baseLineStats 
        // ArrayList and output data to a file.
        exportToFile();
    }

    // Method will take in the totalList ArrayList and baseLineStats 
    // Creates and writes data to file.
    public void  exportToFile()
    {
        File dataFile = new File("data.txt");
        try
        {
            // Create the data file.
            if (dataFile.exists()) 
            {
                dataFile.delete();
                dataFile.createNewFile();
            }
        }
        catch (IOException e) 
        {
            System.out.println("Fail to create data.txt");
            System.exit(0);
        }
        
        // Writing to the data.txt
        try 
        {
            FileWriter fw = new FileWriter(dataFile);

            String header = String.format("%-15s", " ");
            for (int u = 0; u < totalList.size(); u++)
            {
                header = header + String.format("%-10s", ":Day " + (u+1) + ":");
            }
            fw.write(header + "\n");
                
            for (int k = 0; k < totalList.get(0).size(); k++)
            {// Loop by event
                // Print out by event name
                String temp = "";
                temp = temp + totalList.get(0).get(k).getEventName();
                temp = String.format("%-15s", temp);

                // Loop by day
                for (int j = 0; j < totalList.size(); j++)
                {
                    String temp2 = String.valueOf(totalList.get(j).get(k).getValue());
                    temp2 = ":" + temp2 + ":";
                    temp2 = String.format("%-10s", temp2);
                    temp = temp + temp2;
                }
                fw.write(temp + "\n");
            }
            fw.write("\n");

            // Header for Base Line Stats
            String temp3 = String.format("%-15s", "Event name") + ":";
            temp3 = temp3 + String.format("%-7s", "Mean") + ":";
            temp3 = temp3 + String.format("%-18s", "Standard Deviation") + ":";
            fw.write(temp3 + "\n");


            for (int g = 0; g < baseLineStats.size(); g++)
            {
                String compile = String.format("%-15s", baseLineStats.get(g).getEventName()) + ":";
                compile = compile + String.format("%-7s", baseLineStats.get(g).getMean()) + ":";
                compile = compile + String.format("%-18s", baseLineStats.get(g).getStandardDeviation()) + ":";
                fw.write(compile + "\n");
            }       
            fw.close();
        } 
        catch (IOException e) 
        {
            System.out.println("Fail to write to data.txt");
            System.exit(0);
        }
    }

    public void calculateTotals(ArrayList<events> eventList)
    {
        // Loop through for each event 
        int numberOfDays = logList2.size();
        int numberOfEventsPerDay = logList2.get(0).size();
        
        for (int i = 0; i < numberOfDays; i++)
        {
            // Calculate the "Daily Totals"
            ArrayList<logs> lg = new ArrayList<logs>();
            double sum = 0.0;
            for (int m = 0; m < numberOfEventsPerDay; m++)
            {
                double value = logList2.get(i).get(m).getValue();
                sum = sum + value;
            }

            // Rounding off mean to 2 Decimal Places.
            DecimalFormat formatDecimal = new DecimalFormat("0.00");
            // Format decimal outputs a string, so we need to convert it
            String temp1 = formatDecimal.format(sum);
            double output1 = Double.valueOf(temp1);
           
            lg.add(new logs("Daily Total ", output1)); 

            // calculate for each event on each day
            for (int k = 0; k < numberOfEventsPerDay; k++)
            {
                // Retrieve data
                String eventName = baseLineStats.get(k).getEventName();
                double weight = eventList.get(k).getWeight();
                double mean = baseLineStats.get(k).getMean();
                double standardDeviation = baseLineStats.get(k).getStandardDeviation();                
                double value = logList2.get(i).get(k).getValue();

                // Applying formula...
                double step1 = Math.abs(mean - value);
                double step2 = step1 / standardDeviation;
                double step3 = step2 * weight;

                // Rounding off mean to 2 Decimal Places.
                // Format decimal outputs a string, so we need to convert it
                String temp = formatDecimal.format(step3);
                double output = Double.valueOf(temp);
                
                // Adding to temporary ArrayList for events for each day
                lg.add(new logs(eventName, output));                
            }            
            // Adding to ArrayList for each day
            totalList.add(lg); 
        }
        
        // Display what has been read in to user
        System.out.println("");
        for (int k = 0; k < totalList.size(); k++)
        {
            System.out.println("Day " + (k + 1));
            int tempo = totalList.get(0).size();
            for (int j = 0; j < tempo; j++)
            {
                System.out.println(totalList.get(k).get(j));
            }
            System.out.println("");
        }       
    }

    // Method takes in the list of log data extracted from log.txt
    // and calculates the mean and standard deviation
    public void calculateBaselineStats()
    {
        System.out.println("Calculating Baseline Stats...\n");
        // Loop through for each event 
        int numberOfEventsPerDay = logList2.get(0).size();
        for (int i = 0; i < numberOfEventsPerDay; i++)
        {
            //==================================================
            // Calculating Mean for each event
            //==================================================
            String eventName = logList2.get(0).get(i).getEventName();
            double mean = 0.0;
            double sum = 0.0;
            double numberOfDays = logList2.size();
            for (int k = 0; k < logList2.size(); k++) // loop through each day in loglist
            {sum = sum + logList2.get(k).get(i).getValue();} // Add value for each day
                
            // Rounding off mean to 2 Decimal Places.
            DecimalFormat formatDecimal = new DecimalFormat("0.00");
            // Format decimal outputs a string, so we need to convert it
            String tempMean = formatDecimal.format(sum / numberOfDays);
            mean = Double.valueOf(tempMean);

            //==================================================
            // Calculating Standard Deviation for each event
            //==================================================            
            double totalDifference = 0.0;
            for (int k = 0; k < logList2.size(); k++) // loop through each day in loglist
            {
                double difference = 0.0;

                //Step 1: Take Mean and Subtract from each value. Then take the Absolute value as the result
                difference = Math.abs(mean - logList2.get(k).get(i).getValue());

                // Step 2: Square each of the differences
                // Step 3: Add all the differences up
                totalDifference = totalDifference + Math.pow(difference, 2);
            }
            // Step 4: Take the sum of the differences and divide by the number of initial records
            double variance = totalDifference / numberOfDays;

            // Step 5: Square Root the variance
            double SD = Math.sqrt(variance);

            // Rounding off mean to 2 Decimal Places.
            String tempSD = formatDecimal.format(SD);
            double standardDeviation = Double.valueOf(tempSD);

            // Add event name, mean and standard deviation to the list
            baseLineStats.add(new stats(eventName, mean, standardDeviation));
        }

        // Displaying the base line stats calculated
        System.out.println("Here are the base line stats calculated from log.txt...");
        String temp = String.format("%-15s", "Event name") + ":";
        temp = temp + String.format("%-7s", "mean") + ":";
        temp = temp + String.format("%-18s", "standard deviation") + ":";
        System.out.println(temp);
        for (stats s : baseLineStats) {System.out.println(s);}
    }

    // Method reads in the entire log file and created a 2D array of log objects
    public void readInLogFile()
    {
        System.out.println("\nReading in log.txt file...");
        try 
        {
            File logFile = new File("log.txt");  // File object
            Scanner scn = new Scanner(logFile);  // Scanner object

            int eventCount = 0;
            int daysCount = 0;
            while (scn.hasNextLine()) 
            {
                String isNewDay = scn.nextLine();  // Read Line

                // check '#' for the start of new day in log.txt
                if (isNewDay.charAt(0) == '#')     // A new day in log
                { 
                    eventCount = Integer.valueOf(scn.nextLine());   // Set event count to number 
                    daysCount++; // Increment for number of days
                    ArrayList<logs> rawlog = new ArrayList<logs>();
                    
                    for (int i = 0; i < eventCount; i++)
                    {
                        
                        String temp = scn.nextLine();
                        String[] line = temp.split(":");   // Delimit line
                        String eventName = line[0];
                        double value = Double.valueOf(line[1]);
                        rawlog.add(new logs(eventName, value));
                    }
                    logList2.add(rawlog);
                } 
                else {} // Program concinues searching for the next #Day which indicates a new day
            }
        }
        catch (IOException ex) 
        {
            System.err.println(ex);
            System.exit(0);
        }

        // Display what has been read in to user
        for (int k = 0; k < logList2.size(); k++)
        {
            System.out.println("Day " + (k + 1));
            int tempo = logList2.get(k).size();
            for (int j = 0; j < tempo; j++)
            {
                System.out.println(logList2.get(k).get(j));
            }
            System.out.println("");
        }
        System.out.println("Data read in from log.txt successfully!\n");
    }
}
