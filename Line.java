import java.util.*;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Anthony Vigil
 */
public class Line {
    ArrayList<Customer> customers = new ArrayList();
    int idInt;
    int size;
    Customer currentCustomer;
    Teller teller;
    
    
    
    public Line(int idInt, Teller teller) {
    	this.idInt = idInt;
    	this.teller = teller;
    }
    
    
    //set methods
    public void setTeller(Teller teller) {
    	this.teller = teller;
    }
    public void addCustomer(Customer c) {
        size++;
        customers.add(c);
    }
    public void setId(int idInt) {
        this.idInt = idInt;
    }
    public void setCurrentCustomer(int index) {
    	System.out.println("happening");
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
    public void nextCustomer() {
    	int index = customers.indexOf(currentCustomer)+1;
    	if(index < customers.size()) {
    		currentCustomer = customers.get(index);
    	} 
    }
    public Customer getCurrentCustomer() {
    	return currentCustomer;
    }
    public Teller getTeller() {
    	return teller;
    }
    public ArrayList<Customer> getCustomerQueue() {
        return customers;
    }
    public int getSize() {
        return size;
    }
    public int getIdInt() {
        return idInt;
    }
    public double getEstimatedTime() {
        double tot = 0;
        double tellerConstant = teller.getSpeed();
        ArrayList<Customer> temp = customers;
        
        for(int i = 0; i < temp.size(); i++) {
            tot += 
            		((temp.get(i).getSpeed() * tellerConstant)/10); //time is customer * teller /10
        }
        
        return tot;
    }
    
    
    
}
