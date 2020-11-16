// Package
package main;

// Driver Class
public class MainSys
{
    public static void main(String[] args) 
    {
        // Create new IDS object
        IDS ids = new IDS();

        // check for the right number of args length
        if (args.length == 4) 
        {
            int days = Integer.parseInt(args[3]); // get days 

            // Checks if parameters are correct
            boolean valid = ids.validateInput(args);
            
            if(valid == true)
            {
                System.out.println("Valid Events.txt and Stats.txt");
                System.out.println("\nProceeding to read in files...");
                // args[1] -> Events.txt || args[2] -> Stats.txt
                ids.startInput(args[1], args[2]);
            }
            else
            {System.out.println("Invalid arguments");}     
        } 
        else 
        {
            System.out.println("Invalid arguments or length");
        }
    }
}
