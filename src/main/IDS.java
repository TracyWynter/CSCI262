// Package
package main;

// Driver Class
import java.util.ArrayList;

public class IDS {

    public static void main(String[] args) {
        // Class Object
        Input ipt = new Input();
        SimulateEngine sim = new SimulateEngine();
        AnalysisEngine analysis = new AnalysisEngine();
        AlertEngine alert = new AlertEngine();

        // Custom File Objects
        ArrayList<events> eventList = new ArrayList<>();  // Used in input 
        ArrayList<stats> statList = new ArrayList<>();    // Used in input
        ArrayList<stats> newStatList = new ArrayList<>(); //Used in Alert Engine
        
        System.out.println("Verifying Integerity of Event.txt and Stats.txt...");

        // check for the right number of args length
        if (args.length == 4) {
            // Checks if parameters are correct
            boolean valid = ipt.validateInput(args);

            int days = Integer.valueOf(args[3]); // get days 

            if (valid == true) {
                System.out.println("Valid Events.txt and Stats.txt");
                System.out.println("\nProceeding to read in files...");
                // args[1] -> Events.txt || args[2] -> Stats.txt
                ipt.startInput(args[1], args[2], eventList, statList); // initial input
 
                sim.simulateEngine(days, statList, eventList); // simulate engine
                //analysis.analysisEngine(); 
                //alert.alertEngine(newStatList, eventList); //alertEngine

                //sim.simulateEngine(days, statList, eventList); // simulate engine
                analysis.analysisEngine(days); // analyse baseline data
                alert.alertEngine(newStatList, eventList); //alertEngine

            } else {
                System.out.println("Invalid arguments");
            }
        } else {
            System.out.println("Invalid arguments or length");
        }
    }
}
