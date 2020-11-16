/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author yanyi
 */
public class SimulateEngine {
    // activity engine and the logs 

    public void simulateEngine() {
        // Printing current progress 
        System.out.println("File Reading Completed");
        System.out.println("Begin generating and logging ....");
        // [2a] Generate Event
        generateEvents();
        // [2b] Loggin Event
        logEvents();
    }

    public void generateEvents() {
        // Stats: [Event Name:Mean:SD]

    }

    public void logEvents() {
        // Save into 1 file
    }
}
