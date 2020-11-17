package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import main.stats;

public class SimulateEngine {
    // activity engine and the logs 

    public void simulateEngine(int days, ArrayList<stats> statList) {
        // Printing current progress 
        System.out.println("\nFile Reading Completed");
        System.out.println("Begin generating and logging ....");
        // [2a] Generate Event
        generateEvents(days, statList);
        // [2b] Loggin Event
        logEvents();
    }

    // Test 5 days
    private void generateEvents(int days, ArrayList<stats> statList) {
        // Stats: [Event Name:Mean:SD]
//        int eventCount = generateInt(1,5); // not sure if we need to gen event count
        ArrayList <String> listOfEvents = generateEventList(statList);
        // Generate and Store 
        ArrayList<stats> newStatsList = new ArrayList<>();
        // Outter loop (loop for num of 'days')
        for (int i = 0; i < days; i ++){
            // Inner loop (loop for the number of events)
            for (int j = 0; j < listOfEvents.size(); j++)
            {
                String eventName = listOfEvents.get(j);
//                double mean = ;
//                double sd = ;
//                newStatsList.add(new stats(eventName, ));
            }
        }

        //  Log up to number of 'days'
        File logFile = new File("log.txt");
        try {
            // create file 
            if (logFile.exists()) {
                logFile.delete();
                logFile.createNewFile();
            }
        } catch (IOException e) {
           System.out.println("Fail to create log.txt");
        }
        
        // Writing to log.txt
        try{
            FileWriter fw = new FileWriter(logFile);
            for (stats s: newStatsList){
                fw.write(s.toString()); // writing to file
                // Debug
                System.out.println(s);
            }
            
            
            fw.close();
        }
        catch(IOException e){
            System.out.println("Fail to write to log.txt");
        }

    }
    
    // Number generation for the Stats file 
    // I have not set the min and max
    public int generateInt(int min, int max){
        int num = (int) (Math.random() * (max - min + 1) + min);
        return num;
    }
    
    public double generateDouble(int min, int max){
        double num = Math.random() * (max - min + 1) + min;
        return num;     
    }
    
    // Generate the randomise event list based on the given one
    public ArrayList<String> generateEventList(ArrayList<stats> statList){
        ArrayList<String> listOfEvents = new ArrayList<>();
        for (int i = 0; i < statList.size(); i++){
            listOfEvents.add(statList.get(i).getEventName());
        }
        Collections.shuffle(listOfEvents);
        return listOfEvents;
    }

    private void logEvents() {
        // Save into 1 file
    }
}
