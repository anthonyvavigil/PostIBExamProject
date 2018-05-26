/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Henry Estberg and Tony Vigil
 */
public class Teller {
    private String id;
    private int idInt;
    private double timeToProcess; //represents seconds it takes for the teller to deal with an order.
    private int transTime; //Time remaining to process current transaction
    
    //This is meant to be instantiated only with complete paramaters at the beginning of the simulation
    public Teller(String id, int idInt, double timeToProcess) { 
        this.timeToProcess = timeToProcess;
    }

    //set methods
    public void setTimeToProcess(double timeToProcess) {
        this.timeToProcess = timeToProcess;
    } 
    public void setId(String id) {
        this.id = id;
    }
    public void setId(int idInt) {
        this.idInt = idInt;
    }
    public void setTransTime(int transTime) {
        this.transTime = transTime;
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
    public int getTransTime() {
        return this.transTime;
    }
}
