package main;

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

    private void generateEvents() {
        // Stats: [Event Name:Mean:SD]

    }

    private void logEvents() {
        // Save into 1 file
    }
}
