package main;

import java.util.Arrays;

public class IDS {
    
    /* MAIN METHODS */

    /* initial input */
    public void startInput() {

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
    public boolean validateInput(String[] args){
        boolean [] valid = new boolean[4];
        Arrays.fill(valid,false);
        // check 1st arg
        if (args[0].equals("IDS")){
            valid[0]= true;
        }
        
        // check 2nd arg
        if (args[1].equals("Events.txt")){
            valid[1]=true;
        }
        
        // check 3rd arg
        if(args[2].equals("Stats.txt")){
            valid[2]=true;
        }
        
        // check 4th arg (must be integer)
        
        valid[3] = false;
        
        if(!Arrays.asList(valid).contains(false)){
            return true;
        }
        
        return false;
    }



}
