// Package
package main;

public class stats 
{
    // Variables
    private String eventName;
    private double mean;
    private double standardDeviation;

    // Default Constructor
    public stats()
    {
        setEventName("");
        setMean(0.0);
        setStandardDeviation(0.0);
    }

    // Main Constructor
    public stats(String eventName, double mean, double standardDeviation)
    {
        setEventName(eventName);
        setMean(mean);
        setStandardDeviation(standardDeviation);
    }
    
    // Copy Constructor
    public stats(stats s)
    {
        setEventName(s.getEventName());
        setMean(s.getMean());
        setStandardDeviation(s.getStandardDeviation());
    }

    // Accessor Method
    public String getEventName()
    {
        return eventName;
    }
    public double getMean()
    {
        return mean;
    }
    public double getStandardDeviation()
    {
        return standardDeviation;
    }

    // Mutator Method 
    public void setEventName(String eventName)
    {
        this.eventName = eventName;
    }
    public void setMean(double mean)
    {
        this.mean = mean;
    }
    public void setStandardDeviation(double standardDeviation)
    {   
        this.standardDeviation = standardDeviation;
    }

    // Display Method
    @Override
    public String toString() 
    { 
        return String.format(eventName + ":" + mean + ":" + standardDeviation); 
    }
}
