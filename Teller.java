import java.util.ArrayList;

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
    private int idInt;
    private int speed; //represents seconds it takes for the teller to deal with an order.
    private int transTime; //Time remaining to process current transaction
    public Customer currentCustomer;
    
    //This is meant to be instantiated only with complete paramaters at the beginning of the simulation
    public Teller(int idInt, int speed) { 
    	this.idInt = idInt;
        this.speed = speed;
    }

    //set methods
    public void setSpeed(int speed) {
        this.speed = speed;
    } 
    public void setId(int idInt) {
        this.idInt = idInt;
    }
    public void setTransTime(int transTime) {
        this.transTime = transTime;
    }
    public void setCurrentCustomer(Customer c) {
    	currentCustomer = c;
    }
    public void nextCustomer(ArrayList<Customer> customers) {
    	int index = customers.indexOf(currentCustomer)+1;
    	if(index < customers.size()) {
    		currentCustomer = customers.get(index);
    	} 
    }
    public void setCurrentCustomer(int index, ArrayList<Customer> customers) {
    	System.out.println("setting current customer");
    	if(index > 0 && index < customers.size()) {
    		currentCustomer = customers.get(index);
    		System.out.println("successfully set customer");
    	} else if (index == 0 && !customers.isEmpty()){
    		currentCustomer = customers.get(0);
    		System.out.println("index was 0");
    	} else {
    		System.out.println("error setting customer, index : " + index + ", customer size : " + customers.size() + ", isEmpty : " + customers.isEmpty());
    	}
    }
    
    
    //get methods
    public int getIdInt() {
        return idInt;
    }
    public int getSpeed() {
        return speed;
    }
    public int getTransTime() {
        return this.transTime;
    }
}
