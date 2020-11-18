// Package
package main;

public class logs 
{
    // Variables
    private String eventName;
    private double value;

    // Default Constructor
    public logs()
    {
        setEventName("");
        setValue(0.0);    
    }

    // Main Constructor
    public logs(String eventName, double value)
    {
        setEventName(eventName);
        setValue(value);
    }

    // Copy Constructor
    public logs(logs l)
    {
        setEventName(l.getEventName());
        setValue(l.getValue());
    }

    // Accessor Method
    public String getEventName()
    {
        return eventName;
    }   
    public double getValue()
    {
        return value;
    }

    // Mutator Method 
    public void setEventName(String eventName)
    {
        this.eventName = eventName;
    }
    public void setValue(double value)
    {
        this.value = value;
    }

    // Display Method
    @Override
    public String toString() 
    {        
        String temp = String.format("%-15s", eventName) + ":";
        temp = temp + String.format("%-7s", value) + ":";
        return temp;
    }
}
