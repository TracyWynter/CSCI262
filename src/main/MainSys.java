// Package
package main;

// Driver Class
public class MainSys {

    public static void main(String[] args) {
        // Class Object
        Input ipt = new Input();
        SimulateEngine sim = new SimulateEngine();
        AnalysisEngine analysis = new AnalysisEngine();
        AlertEngine alert = new AlertEngine();
        
        // Custom File Objects
        

        // check for the right number of args length
        if (args.length == 4) {
            int days = Integer.parseInt(args[3]); // get days 

            // Checks if parameters are correct
            boolean valid = ipt.validateInput(args);

            if (valid == true) {
                System.out.println("Valid Events.txt and Stats.txt");
                System.out.println("\nProceeding to read in files...");
                // args[1] -> Events.txt || args[2] -> Stats.txt
                ipt.startInput(args[1], args[2]);
                
            } else {
                System.out.println("Invalid arguments");
            }
        } else {
            System.out.println("Invalid arguments or length");
        }
    }
}
