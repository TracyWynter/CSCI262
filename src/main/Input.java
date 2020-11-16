// Package
package main;

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
    ArrayList<String> eventList = new ArrayList<>();
    ArrayList<String> cdEventList = new ArrayList<>();
    ArrayList<Integer> minimumList = new ArrayList<>();
    ArrayList<Integer> maximumList = new ArrayList<>();
    ArrayList<Integer> weightList = new ArrayList<>();

    // To store date from Stats.txt
    ArrayList<String> statsEventList = new ArrayList<>();
    ArrayList<Double> meanList = new ArrayList<>();
    ArrayList<Double> sdList = new ArrayList<>();

    // ---------- MAIN METHODS -----------------
    // initial input 
    public void startInput(String eventsFile, String statsFile) {
        // Extract information from both files and store them 
        readEventFile(eventsFile); // Read from Events.txt
        readStatFile(statsFile);   // Read from Stats.txt

    }

    // -------------- SUB METHODS --------------
    // Method reads in information from Events.txt file
    public void readEventFile(String eventFile) {
        try {
            File fileObj = new File(eventFile); // Create new File opbject
            Scanner sct = new Scanner(fileObj); // Create new Scanner object

            String numberOfEvents = sct.nextLine(); //Reading the first line of the file
            int eventCount = Integer.valueOf(numberOfEvents); // Convert to Integer

            int n = 0; // Counter to check number or records
            while (sct.hasNextLine()) {
                String eventText = sct.nextLine(); // Takes in next line
                String[] eventLine = eventText.split(":"); // Delimit 

                // Add to ArrayLists
                eventList.add(eventLine[0]);    // Stores event name
                cdEventList.add(eventLine[1]);  // Stores event type
                minimumList.add(Integer.valueOf(eventLine[2])); // Stores minimum value

                if (eventLine[1].equals("D")) {
                    boolean isInteger = false;
                    int min = 0;
                    try {
                        min = Integer.parseInt(eventLine[2]);
                        isInteger = true;
                        minimumList.add(Integer.valueOf(eventLine[2]));
                    } catch (NumberFormatException e) {
                        System.out.println("Error! Minimum value is not an integer");
                        System.exit(0);
                    }

                    int maximum;
                    try { //Test for blank spacing
                        maximum = Integer.parseInt(eventLine[3]);
                    } catch (NumberFormatException ex) {
                        maximum = 0;
                    }

                    if (min > maximum) {
                        System.out.println("Error! Minimum value cannot be larger than Maximum value");
                    }
                    maximumList.add(maximum);
                } else if (eventLine[1].equals("C")) {

                    minimumList.add(Integer.valueOf(eventLine[2])); // Stores minimum value

                    int maximum;
                    try {
                        maximum = Integer.parseInt(eventLine[3]);
                    } catch (NumberFormatException ex) {
                        maximum = 0;
                    }

                    maximumList.add(maximum);

                }

                if (Integer.parseInt(eventLine[4]) > 0) {
                    weightList.add(Integer.valueOf(eventLine[4]));
                } else {
                    System.out.println("Error! Weight cannot be negative!");
                }
                n++;
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

        } catch (IOException e) {
            System.err.println(e);
            System.exit(0);
        }

        // Debug code
        //System.out.println(eventList);
        //System.out.println(cdEventList);
        //System.out.println(minimumList);
        //System.out.println(maximumList);
        //System.out.println(weightList);
    }

    public void readStatFile(String statsFile) {
        try {
            File fileObj = new File(statsFile); // Create new File opbject
            Scanner sct = new Scanner(fileObj); // Create new Scanner object

            String numberOfEvents = sct.nextLine(); //Reading the first line of the file
            int eventCount = Integer.valueOf(numberOfEvents); // Convert to Integer

            int n = 0; // Counter to check number or records
            while (sct.hasNextLine()) {
                String eventText = sct.nextLine(); // Takes in next line
                String[] statsLine = eventText.split(":"); // Delimit 

                statsEventList.add(statsLine[0]); // Stores Event name
                meanList.add(Double.valueOf(statsLine[1])); // Stores Mean value
                sdList.add(Double.valueOf(statsLine[2])); // Stores Standard Deviation
                n++;
            }
            sct.close();

            if (n == eventCount) // [3] Check number of events against first digit in the file.
            {// If it matches
                System.out.println("Successfully read in " + statsFile + "!");
            } else {// If it does not match
                System.out.println("Failed to read in " + statsFile + "!");
                System.out.println("Number of records specified does not match the number of records listed!");
                System.out.println("Program will terminate!");
                System.exit(0);
            }
        } catch (IOException e) {
            System.err.println(e);
            System.exit(0);
        }

        // Debug Code
        //System.out.println(statsEventList);
        //System.out.println(meanList);
        //System.out.println(sdList);
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
