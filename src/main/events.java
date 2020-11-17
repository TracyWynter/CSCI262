// Package
package main;

public class events 
{
    // Variables
    private String eventName;
    private char eventType;
    private double minimum;
    private double maximum;
    private Integer weight;

    // Default Constructor
    public events()
    {
        setEventName("");
        setEventType('D');
        setMinimum(0.0);
        setMaximum(0.0);
        setWeight(0);
    }

    // Main Constructor
    public events(String eventName, char eventType, double minimum, double maximum, Integer weight)
    {
        setEventName(eventName);
        setEventType(eventType);
        setMinimum(minimum);
        setMaximum(maximum);
        setWeight(weight);
    }

    // Copy Constructor
    public events(events e)
    {
        setEventName(e.getEventName());
        setEventType(e.getEventType());
        setMinimum(e.getMinimum());
        setMaximum(e.getMaximum());
        setWeight(e.getWeight());
    }

    // Accessor Method
    public String getEventName()
    {
        return eventName;
    }   
    public char getEventType()
    {
        return eventType;
    }
    public double getMinimum()
    {
        return minimum;
    }
    public double getMaximum()
    {
        return maximum;
    }
    public Integer getWeight()
    {
        return weight;
    }

    // Mutator Method 
    public void setEventName(String eventName)
    {
        this.eventName = eventName;
    }
    public void setEventType(char eventType)
    {
        this.eventType = eventType;
    }
    public void setMinimum(double minimum)
    {
        this.minimum = minimum;
    }
    public void setMaximum(double maximum)
    {
        this.maximum = maximum;
    }
    public void setWeight(Integer weight)
    {
        this.weight = weight;
    }

    // Display Method
    @Override
    public String toString() 
    {        
        String temp = String.format("%-15s", eventName) + ":";
        temp = temp + String.format("%-4s", eventType) + ":";
        temp = temp + String.format("%-7s", minimum) + ":";
        temp = temp + String.format("%-7s", maximum) + ":";
        temp = temp + String.format("%-6s", weight) + ":";
        return temp;
        //return String.format(eventName + ":" + eventType + ":" + minimum + ":" + maximum + ":" + weight); 
    }
}
