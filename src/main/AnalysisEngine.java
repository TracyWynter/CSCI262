package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Math.abs;
import static java.lang.Math.pow;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import main.logs;

public class AnalysisEngine {

    // analysis engine 
    ArrayList<logs> logList = new ArrayList<>();
    ArrayList<stats> baseLineStatsList = new ArrayList<>();
    ArrayList<String> dailyTotals = new ArrayList<>();

    public void analysisEngine(int days) {
        // Printing current progress 
        System.out.println("\nEvent Generation Completed");
        System.out.println("Begin Analysing........");
        // Measure baseline data  (generated Baseline Stats)
        measureData(days);
        generateDataFile();
        
    }

    // Get Mean and SD
    private void measureData(int days) {
        System.out.println("\nReading in log.txt....");

        // Read in to ArrayList first
        try {
            File logFile = new File("log.txt");  // File object
            Scanner scn = new Scanner(logFile);     // Scanner object
            // check '#' for the start of new daily log
            int eventCount = 0;
            int daysCount = 0;
            while (scn.hasNextLine()) {
                String logLine = scn.nextLine();  // Read Line
                if (logLine.charAt(0) == '#') { // A new daily log
                    eventCount = Integer.valueOf(scn.nextLine());   //  Use for next step (Do we need to check for integer?)
                    daysCount++;
                } else {
                    String[] line = logLine.split(":");   // File line
                    String eventName = line[0];
                    double value = Double.valueOf(line[1]);
                    logList.add(new logs(eventName, value));
                }

            }
            // After storing the file 
            DecimalFormat df2 = new DecimalFormat("#.00");  // format to 2.d.p (for mean and SD)
            //int dayCounter = 0;
            double totalValue = 0.0;
            // Add for every Event
            for (int i = 0; i < eventCount; i++) {
                double[] valArr = new double[days];     // Store the values for SD calculation
                int count = 0;  // counting the same event for 'days'
                for (int c = eventCount; c < logList.size(); c++) {
                    if (logList.get(i).getEventName().equals(logList.get(c).getEventName())) {
                        totalValue += logList.get(c).getValue();    // sum the value
                        valArr[count] = logList.get(c).getValue();  // store all value in for SD formula
                        count++;
                    }
                }
                // Reset for next event (Calculation for each event)
                double mean = Double.valueOf(df2.format(totalValue / daysCount));   // Mean
//                double mean =  Double.valueOf(String.format("%.2f", (totalValue/daysCount)));
                count = 0;
                totalValue = 0.0; // Sum
                double sumOfDiff = 0.0;
                // Loop and add the difference
                for (int j = 0; j < valArr.length; j++) {
                    sumOfDiff += pow(abs(mean - valArr[j]), 2);
                }
                double sd = Double.valueOf(df2.format(Math.sqrt(sumOfDiff / days)));    // Variance = sumOfDiff/daysCount
                baseLineStatsList.add(new stats(logList.get(i).getEventName(), mean, sd));
            } // End of Mean and SD Calculation
            // Getting daily totals
            dailyTotals.add(String.valueOf(days));
            double val = 0.0;
            int dayTrack = 0;
            int count = 0;

            for (logs l : logList) {
                val += l.getValue();
                count++;
                if (count == eventCount) {
                    dayTrack++;
                    dailyTotals.add("Day " + dayTrack + ":" + df2.format(val) + ":");
                    count = 0;
                    val = 0;
                }

            }

        } // End of Daily Total  Calculation
        catch (IOException ex) {
            System.err.println(ex);
            System.exit(0);
        }
        // Debug
        System.out.println(
                "Determining " + "logFile.txt" + " statistics:");
        String temp = String.format("%-15s", "Event name") + ":";
        temp = temp + String.format("%-7s", "mean") + ":";
        temp = temp + String.format("%-18s", "standard deviation") + ":";

        System.out.println(temp);
        for (stats s : baseLineStatsList) {
            System.out.println(s);
        }
    }
// Save the data for the number of 'days'

    private void generateDataFile() {
        File dataFile = new File("data.txt");
        try {
            //create the data file
            if (dataFile.exists()) {
                dataFile.delete();
                dataFile.createNewFile();

            }
        } catch (IOException e) {
            System.out.println("Fail to create data.txt");
            System.exit(0);
        }

        // Writing to the data.txt
        try {
            FileWriter fw = new FileWriter(dataFile);
            for (String s : dailyTotals) {             
                fw.write(s + "\n");
                System.out.println(s);
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Fail to write to data.txt");
            System.exit(0);
        }

    }
}
