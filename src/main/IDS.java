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

public class IDS {

    /* MAIN METHODS */

 /* initial input */
    public void startInput(String eventsFile, String statsFile) {
        readStatFile(statsFile);
        readEventFile(eventsFile);
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
    public static void readStatFile(String statsName) {

        String statsFile = "src/main/" + statsName;
        //file name is hardcoded in
        File myFile = new File("main/Stats.txt");
        try {
            //Scanner sct = new Scanner (myFile);

            //Reading the first line of the file
            int n = 0;
            String numberOfEvents = Files.readAllLines(Paths.get(statsFile)).get(n);
            //Printing the first line 
            //System.out.println(numberOfEvents);
            int eventCount = Integer.parseInt(numberOfEvents);

            if (n < eventCount) {
                //Reading the second line of the file
                String logins = Files.readAllLines(Paths.get(statsFile)).get(++n);

                /*//Checking line number
            System.out.println("Line number: " + n);
            //Printing the line 
            System.out.println(logins);*/
                //Spliting up to each data in the line
                double meanLogin, sdLogin;

                String[] loginDetails = logins.split(":"); // [Logins , 4, 1.5]
                String eventName1 = loginDetails[0]; // Logins
                meanLogin = Double.parseDouble(loginDetails[1]);
                try {
                    if (loginDetails[1] != null) {
                        meanLogin = Double.parseDouble(loginDetails[1]);
                    }
                } catch (NumberFormatException e) {
                    meanLogin = 0;
                }

                sdLogin = Double.parseDouble(loginDetails[2]);
                try {
                    if (loginDetails[2] != null) {
                        sdLogin = Double.parseDouble(loginDetails[2]);
                    }
                } catch (NumberFormatException e) {
                    sdLogin = 0;
                }

                //Checking if delimited correctly
                System.out.println(eventName1);
                System.out.println(meanLogin);
                System.out.println(sdLogin);

                //Reading the third line of the file
                String timeOnline = Files.readAllLines(Paths.get(statsFile)).get(++n);

                /*//Checking line number
            System.out.println("Line number: " + n);
            //Printing the line 
            System.out.println(timeOnline);*/
                //Spliting up to each data in the line
                double meanTimeOnline = 0;
                double sdTimeOnline = 0;

                String[] timeOnlineDetails = timeOnline.split(":");
                String eventName2 = timeOnlineDetails[0];
                try {
                    if (timeOnlineDetails[1] != null) {
                        meanTimeOnline = Double.parseDouble(timeOnlineDetails[1]);
                    }
                } catch (NumberFormatException e) {
                    meanTimeOnline = 0;
                }

                sdTimeOnline = Double.parseDouble(timeOnlineDetails[2]);
                try {
                    if (timeOnlineDetails[2] != null) {
                        sdTimeOnline = Double.parseDouble(timeOnlineDetails[2]);
                    }
                } catch (NumberFormatException e) {
                    sdTimeOnline = 0;
                }

                /*//Checking if delimited correctly
            System.out.println(eventName2);
            System.out.println(meanTimeOnline);
            System.out.println(sdTimeOnline);*/
                //Reading the fourth line of the file
                String sentMails = Files.readAllLines(Paths.get(statsFile)).get(++n);

                /*//Checking line number
            System.out.println("Line number: " + n);
            //Printing the line 
            System.out.println(sentMails);*/
                //Spliting up to each data in the line
                double meanSentMails = 0;
                double sdSentMails = 0;

                String[] sentMailsDetails = sentMails.split(":");
                String eventName3 = sentMailsDetails[0];
                try {
                    if (sentMailsDetails[1] != null) {
                        meanSentMails = Double.parseDouble(sentMailsDetails[1]);
                    }
                } catch (NumberFormatException e) {
                    meanSentMails = 0;
                }

                sdSentMails = Double.parseDouble(sentMailsDetails[2]);
                try {
                    if (sentMailsDetails[2] != null) {
                        sdSentMails = Double.parseDouble(sentMailsDetails[2]);
                    }
                } catch (NumberFormatException e) {
                    sdSentMails = 0;
                }

                /*//Checking if delimited correctly
            System.out.println(eventName3);
            System.out.println(meanSentMails);
            System.out.println(sdSentMails);*/
                //Reading the fifth line of the file
                String openedMail = Files.readAllLines(Paths.get(statsFile)).get(++n);

                /*//Checking line number
            System.out.println("Line number: " + n);
            //Printing the line 
            System.out.println(openedMail);*/
                //Spliting up to each data in the line
                double meanOpenedMail = 0;
                double sdOpenedMail = 0;

                String[] openedMailDetails = openedMail.split(":");
                String eventName4 = openedMailDetails[0];
                try {
                    if (openedMailDetails[1] != null) {
                        meanOpenedMail = Double.parseDouble(openedMailDetails[1]);
                    }
                } catch (NumberFormatException e) {
                    meanOpenedMail = 0;
                }

                sdOpenedMail = Double.parseDouble(openedMailDetails[2]);
                try {
                    if (openedMailDetails[2] != null) {
                        sdOpenedMail = Double.parseDouble(openedMailDetails[2]);
                    }
                } catch (NumberFormatException e) {
                    sdOpenedMail = 0;
                }

                /*//Checking if delimited correctly
            System.out.println(eventName4);
            System.out.println(meanOpenedMail);
            System.out.println(sdOpenedMail);*/
                //Reading the sixth line of the file
                String sentMail = Files.readAllLines(Paths.get(statsFile)).get(++n);

                /*//Checking line number
            System.out.println("Line number: " + n);
            //Printing the line 
            System.out.println(sentMail);*/
                //Spliting up to each data in the line
                double meanSentMail = 0;
                double sdSentMail = 0;

                String[] sentMailDetails = sentMail.split(":");
                String eventName5 = sentMailDetails[0];
                try {
                    if (sentMailDetails[1] != null) {
                        meanSentMail = Double.parseDouble(sentMailDetails[1]);
                    }
                } catch (NumberFormatException e) {
                    meanSentMail = 0;
                }

                sdSentMail = Double.parseDouble(sentMailDetails[2]);
                try {
                    if (sentMailDetails[2] != null) {
                        sdSentMail = Double.parseDouble(sentMailDetails[2]);
                    }
                } catch (NumberFormatException e) {
                    sdSentMail = 0;
                }

                /*//Checking if delimited correctly
            System.out.println(eventName5);
            System.out.println(meanSentMail);
            System.out.println(sdSentMail);*/
            }

        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static void readEventFile(String eventName) {
        String eventsFile = "src/main/" + eventName;
        try {
            //file name is hardcoded in
            File myFile = new File("/main/Events.txt");
            //Scanner sct = new Scanner (myFile);
            System.out.println(Paths.get(eventsFile).toAbsolutePath());
            //Reading the first line of the file
            int n = 0;
            String numberOfEvents = Files.readAllLines(Paths.get(eventsFile)).get(n);
            //Printing the first line 
            //System.out.println(numberOfEvents);
            int eventCount = Integer.parseInt(numberOfEvents);

            if (n < eventCount) {
                //Reading the second line of the file
                String logins = Files.readAllLines(Paths.get(eventsFile)).get(++n);

                /*//Checking line number
            System.out.println("Line number: " + n);
            //Printing the line 
            System.out.println(logins);*/
                //Spliting up to each data in the line
                int minLogin = 0;
                int maxLogin = 0;
                int weightLogin = 0;

                String[] loginDetails = logins.split(":");
                String eventName1 = loginDetails[0];
                String eventType1 = loginDetails[1];
                minLogin = Integer.parseInt(loginDetails[2]);
                try {
                    if (loginDetails[3] != null) {
                        maxLogin = Integer.parseInt(loginDetails[3]);
                    }
                } catch (NumberFormatException e) {
                    maxLogin = 0;
                }
                weightLogin = Integer.parseInt(loginDetails[4]);

                /*//Checking if delimited correctly
            System.out.println(eventName1);
            System.out.println(eventType1);
            System.out.println(minLogin);
            System.out.println(maxLogin);
            System.out.println(weightLogin);
                 */
                //Reading the third line of the file
                String timeOnline = Files.readAllLines(Paths.get(eventsFile)).get(++n);

                /*//Checking line number
            System.out.println("Line number: " + n);
            //Printing the line 
            System.out.println(timeOnline);*/
                //Spliting up to each data in the line
                int minTimeOnline = 0;
                int maxTimeOnline = 0;
                int weightTimeOnline = 0;

                String[] timeOnlineDetails = timeOnline.split(":");
                String eventName2 = timeOnlineDetails[0];
                String eventType2 = timeOnlineDetails[1];
                minTimeOnline = Integer.parseInt(timeOnlineDetails[2]);
                try {
                    if (timeOnlineDetails[3] != null) {
                        maxTimeOnline = Integer.parseInt(timeOnlineDetails[3]);
                    }
                } catch (NumberFormatException e) {
                    maxTimeOnline = 0;
                }
                weightTimeOnline = Integer.parseInt(timeOnlineDetails[4]);

                /*//Checking if delimited correctly
            System.out.println(eventName2);
            System.out.println(eventType2);
            System.out.println(minTimeOnline);
            System.out.println(maxTimeOnline);
            System.out.println(weightTimeOnline);*/
                //Reading the fourth line of the file
                String sentMails = Files.readAllLines(Paths.get(eventsFile)).get(++n);

                /*//Checking line number
            System.out.println("Line number: " + n);
            //Printing the line 
            System.out.println(sentMails);*/
                //Spliting up to each data in the line
                int minSentMails = 0;
                int maxSentMails = 0;
                int weightSentMails = 0;

                String[] sentMailsDetails = sentMails.split(":");
                String eventName3 = sentMailsDetails[0];
                String eventType3 = sentMailsDetails[1];
                minSentMails = Integer.parseInt(sentMailsDetails[2]);
                try {
                    if (sentMailsDetails[3] != null) {
                        maxSentMails = Integer.parseInt(sentMailsDetails[3]);
                    }
                } catch (NumberFormatException e) {
                    maxSentMails = 0;
                }
                weightSentMails = Integer.parseInt(sentMailsDetails[4]);

                /*//Checking if delimited correctly
            System.out.println(eventName3);
            System.out.println(eventType3);
            System.out.println(minSentMails);
            System.out.println(maxSentMails);
            System.out.println(weightSentMails);
                 */
                //Reading the fifth line of the file
                String openedMail = Files.readAllLines(Paths.get(eventsFile)).get(++n);

                /*//Checking line number
            System.out.println("Line number: " + n);
            //Printing the line 
            System.out.println(openedMail);*/
                //Spliting up to each data in the line
                int minOpenMails = 0;
                int maxOpenMails = 0;
                int weightOpenMails = 0;

                String[] openMailsDetails = openedMail.split(":");
                String eventName4 = openMailsDetails[0];
                String eventType4 = openMailsDetails[1];
                minOpenMails = Integer.parseInt(openMailsDetails[2]);
                try {
                    if (openMailsDetails[3] != null) {
                        maxOpenMails = Integer.parseInt(openMailsDetails[3]);
                    }
                } catch (NumberFormatException e) {
                    maxOpenMails = 0;
                }
                weightOpenMails = Integer.parseInt(openMailsDetails[4]);

                /*//Checking if delimited correctly
            System.out.println(eventName4);
            System.out.println(eventType4);
            System.out.println(minOpenMails);
            System.out.println(maxOpenMails);
            System.out.println(weightOpenMails);
                 */
                //Reading the sixth line of the file
                String deletedMail = Files.readAllLines(Paths.get(eventsFile)).get(++n);

                /*//Checking line number
            System.out.println("Line number: " + n);
            //Printing the line 
            System.out.println(deletedMail);*/
                //Spliting up to each data in the line
                int minDeletedMail = 0;
                int maxDeletedMail = 0;
                int weightDeletedMail = 0;

                String[] deletedMailDetails = deletedMail.split(":");
                String eventName5 = deletedMailDetails[0];
                String eventType5 = deletedMailDetails[1];
                minDeletedMail = Integer.parseInt(deletedMailDetails[2]);
                try {
                    if (deletedMailDetails[3] != null) {
                        maxDeletedMail = Integer.parseInt(deletedMailDetails[3]);
                    }
                } catch (NumberFormatException e) {
                    maxDeletedMail = 0;
                }
                weightDeletedMail = Integer.parseInt(deletedMailDetails[4]);

                //Checking if delimited correctly
                /*System.out.println(eventName5);
            System.out.println(eventType5);
            System.out.println(minDeletedMail);
            System.out.println(maxDeletedMail);
            System.out.println(weightDeletedMail);
                 */
            }

        } catch (IOException e) {
            System.err.println(e);
        }
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
