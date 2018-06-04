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
    ArrayList<Teller> tellers = new ArrayList();
    int idInt;
    int size;
    int index;
    Customer currentCustomer;
    
    
    public Line(int idInt) {
    	this.idInt = idInt;
    }
    
    
    //set methods
    public void setTellers(ArrayList<Teller> tellers) {
    	this.tellers = tellers;
    }
    public void addTeller(Teller teller) {
    	tellers.add(teller);
    }
    public void addCustomer(Customer c) {
        size++;
        customers.add(c);
    }
    public void setId(int idInt) {
        this.idInt = idInt;
    }
    public void setIndex(int index) {
    	this.index = index;
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
    public boolean hasNextCustomer() {
    	int index = customers.indexOf(currentCustomer);
    	if(index < customers.size()-1) {
    		return true;
    	} else {
    		return false;
    	}
    }
    public void nextCustomer() {
    	int index = customers.indexOf(currentCustomer)+1;
    	if(index < customers.size()) {
    		currentCustomer = customers.get(index);
    	} 
    }
    public Customer getCurrentCustomer() {
    	return currentCustomer;
    }
    public ArrayList<Teller> getTellers() {
    	return tellers;
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
    public int getIndex() {
    	return index;
    }
   /* public double getEstimatedTime() {
        double tot = 0;
        double tellerConstant = tellers.getSpeed();
        ArrayList<Customer> temp = customers;
        
        for(int i = 0; i < temp.size(); i++) {
            tot += 
            		((temp.get(i).getSpeed() * tellerConstant)/10); //time is customer * teller /10
        }
        
        return tot;
    }*/
    
    
    
}
