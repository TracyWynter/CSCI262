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
        ArrayList<events> eventList = new ArrayList<>();
        ArrayList<stats> statList = new ArrayList<>();
        
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
                sim.simulateEngine(days, statList); // simulate engine
//                analysis.analysisEngine(); // 

            } else {
                System.out.println("Invalid arguments");
            }
        } else {
            System.out.println("Invalid arguments or length");
        }
    }
}