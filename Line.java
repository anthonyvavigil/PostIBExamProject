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
    Queue<Customer> customers;
    String id;
    int idInt;
    int size;
    Teller teller;
    
    
    
    public Line() {
        size = 0;
    }
    public Line(Queue<Customer> customers) {
        this.customers = customers;
    }
    public Line(String id) {
        this.id = id;
        size = 0;
    }
    public Line(String id, Queue<Customer> customers) {
        this.id = id;
        this.customers = customers;
    }
    
    
    //set methods
    public void setTeller(Teller teller) {
    	this.teller = teller;
    }
    public void addCustomer(Customer c) {
        size++;
        customers.add(c);
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setId(int idInt) {
        this.idInt = idInt;
    }
    
    
    
    //get methods
    public Teller getTeller() {
    	return teller;
    }
    public Queue<Customer> getCustomerQueue() {
        return customers;
    }
    public int getSize() {
        return size;
    }
    public int getIdInt() {
        return idInt;
    }
    public String getId() {
        return id;
    }
    public double getEstimatedTime() {
        double tot = 0;
        double tellerConstant = teller.getTimeToProcess();
        Queue<Customer> temp = customers;
        
        for(int i = 0; i < temp.size(); i++) {
            tot += 
            		((temp.remove().getTimeToProcess() * tellerConstant)/10); //time is customer * teller /10
        }
        
        return tot;
    }
    
    
    
}
