
import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Anthony Vigil
 */
public class Customer {
    private String id;
    private int idInt;
    private Line line;
    private double timeToProcess; //represents seconds that this user will take to order.
    private long tickStart;
    
    public Customer(Line line) {
        this.line = line;
    }
    public Customer(Line line, double timeToProcess) {
        this.line = line;
        this.timeToProcess = timeToProcess;
    }
    
    //set methods
    public void setTimeToProcess(double timeToProcess) {
        this.timeToProcess = timeToProcess;
    }
    public void setLine(Line line) {
        this.line = line;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setId(int idInt) {
        this.idInt = idInt;
    }
    public void setTickStart(long tick) {
        this.tickStart = tick;
    }
    
    //assigns the customer to a line based on number of people in lines
    public void simpleAssign(ArrayList<Line> lines) {
    	int min = lines.get(0).getSize();
    	Line assignedTo = lines.get(0);
    	for(int i = 0; i < lines.size(); i++) {
    		if(lines.get(i).getSize() < min) {
    			min = lines.get(i).getSize();
    			assignedTo = lines.get(i);
    		}
    	}
    	this.line = assignedTo;
    }
    //assigns the customer to a line based on estimated time
    public void timeAssign(ArrayList<Line> lines) {
    	double min = lines.get(0).getEstimatedTime();
    	Line assignedTo = lines.get(0);
    	for(int i = 0; i < lines.size(); i++) {
    		if(lines.get(i).getEstimatedTime() < min) {
    			min = lines.get(i).getEstimatedTime();
    			assignedTo = lines.get(i);
    		}
    	}
    	this.line = assignedTo;
    }
    //assigns the customer to a line randomly
    public void randomAssign(ArrayList<Line> lines) {
    	Random r = new Random();
    	int index = r.nextInt(lines.size());
    	this.line = lines.get(index);
    }
    
    
    //get methods
    public int getIdInt() {
        return idInt;
    }
    public String getId() {
        return id;
    }
    public double getTimeToProcess() {
        return timeToProcess;
    }
    public Line getLine() {
        return line;
    }    
    public long getTickStart() {
        return this.tickStart;
    }
}
