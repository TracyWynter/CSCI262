// Package
package main;

// Import Libraries
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import main.stats;

public class SimulateEngine 
{
    ArrayList<String> newStatsList = new ArrayList<>();
    // activity engine and the logs 

    public void simulateEngine(int days, ArrayList<stats> statList, ArrayList<events> eventList) 
    {
        // Printing current progress 
        System.out.println("\nFile Reading Completed");
        System.out.println("Begin generating and logging ....");
    
        // [2a] Generate Event
        generateEvents(days, statList, eventList, newStatsList);
    
        // [2b] Loggin Event
        logEvents(newStatsList);
    }

    // Test 5 days
    public void generateEvents(int days, ArrayList<stats> statList, ArrayList<events> eventList, 
                                ArrayList<String> nst) 
    {
        // Stats: [Event Name:Mean:SD]
        // Generate and Store 
        DecimalFormat df2 = new DecimalFormat("#.##"); // format to 2.d.p

        // Outter loop (loop for num of 'days')
        for (int i = 0; i < days; i++) {
            nst.add("#Day" + (i + 1));
            nst.add(String.valueOf(eventList.size()));
            // Inner loop (loop for the number of events)
            for (int j = 0; j < eventList.size(); j++) {
                String eventName = eventList.get(j).getEventName();
                double min = (statList.get(j).getMean()) - (2 * statList.get(j).getStandardDeviation());
                double max = (statList.get(j).getMean()) + (2 * statList.get(j).getStandardDeviation());
                // C will get double value
                if (eventList.get(j).getEventType() == 'C') {
                    nst.add(eventName + ":" + df2.format(generateDouble(min, max)) + ":");
                } else { // D will get int value
                    nst.add(eventName + ":" + generateInt(min, max) + ":");
                }

            }
        }
    }

    // Number generation for the Stats file 
    // I have not set the min and max
    private int generateInt(double min, double max) {
        int num = (int) (Math.random() * (max - min + 1) + min);
        return num;
    }

    private double generateDouble(double min, double max) {
        double num = Math.random() * (max - min + 1) + min;
        return num;
    }


    //  Log up to number of 'days'
    private void logEvents(ArrayList<String> mylist) 
    {
        // Save into 1 file
        File logFile = new File("log.txt");
        try {
            // create file 
            if (logFile.exists()) {
                logFile.delete();
                logFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Fail to create log.txt");
            System.exit(0);
        }

        // Writing to log.txt
        try {
            FileWriter fw = new FileWriter(logFile);
            for (String s : mylist) {
                fw.write(s + "\n"); // writing to file
                // Debug Code
                System.out.println(s);
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Fail to write to log.txt");
            System.exit(0);
        }
    }
}
