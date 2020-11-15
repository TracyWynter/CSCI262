package main;

import java.util.Arrays;
import java.io.File;
import java.nio.file.Files;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.NoSuchFileException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IDS {

    /* Events File Storage */
    static List<String> eventList = new ArrayList<>();
    static List<String> cdEventList = new ArrayList<>();
    static List<String> minimumList = new ArrayList<>();
    static List<String> maximumList = new ArrayList<>();
    static List<String> weightList = new ArrayList<>();

    /* Stats File Storage */
    static List<String> statsEventList = new ArrayList<>();
    static List<Double> meanList = new ArrayList<>();
    static List<String> sdList = new ArrayList<>();


    /* MAIN METHODS */

 /* initial input */
    public void startInput(String eventsFile, String statsFile) {
        readStatFile(statsFile);
//        readEventFile(eventsFile);
    }

    /* activity engine and the logs */
    public void simulateEngine() {

    }

    /* analysis engine */
    public void analysisEngine() {

    }

    /* alert engine */
    public void alertEngine() {

    }

    /* SUB METHODS */
    public static void printList(List<String> list) {
        for (String s : list) {
            System.out.println(s);
        }
    }

    public static void readStatFile(String file) {
//        String statsFile = "src/main/" + file;  // netbeans
        String statsFile = file;
        try {
            //Reading the first line of the file
            int n = 0;
            String numberOfEvents = Files.readAllLines(Paths.get(statsFile)).get(n);
            //Printing the first line 
            //System.out.println(numberOfEvents);
            int eventCount = Integer.parseInt(numberOfEvents);

            while (n < eventCount) {
                String statsText = Files.readAllLines(Paths.get(statsFile)).get(++n);
                String[] statsLine = statsText.split(":");
                statsEventList.add(statsLine[0]);
                meanList.add(Double.parseDouble(statsLine[1]));
                sdList.add(statsLine[2]);

            }

        } catch (IOException e) {
            System.err.println(e);
        }
        printList(statsEventList);

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
        valid[3] = false;

        if (!Arrays.asList(valid).contains(false)) {
            return true;
        }

        return false;
    }

}
