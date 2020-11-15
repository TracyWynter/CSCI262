
package main;

public class MainSys {

    public static void main(String[] args) {
        IDS ids = new IDS();

        /* check for the right args length*/
        if (args.length == 4) {
            int days = Integer.parseInt(args[3]); // get days 
            boolean valid = ids.validateInput(args);
            if(valid){
                System.out.println("Valid");
            }
            
            ids.startInput(args[1], args[2]);
            
        } else {
            System.out.println("Invalid number of arguments");
        }

    }
}
