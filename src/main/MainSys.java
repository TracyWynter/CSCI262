
package main;

public class MainSys {

    public static void main(String[] args) {
        IDS ids = new IDS();

        /* check for the right args length*/
        if (args.length == 4) {
            int days = Integer.parseInt(args[3]); // get days 
            System.out.println(days);
            
            ids.startInput();
            
        } else {
            System.out.println("Invalid number of arguments");
        }

    }
}
