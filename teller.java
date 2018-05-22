/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package postibexamproject;

/**
 *
 * @author Henry Estberg
 */
public class teller {
    private String id;
    private int idInt;
    private double timeToProcess; //represents seconds it takes for the teller to deal with an order.
    
    //This is meant to be instantiated only with complete paramaters at the beginning of the simulation
    public teller(String id, int idInt, double timeToProcess) { 
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
}
