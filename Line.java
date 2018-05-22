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
    Queue<customer> customers;
    String id;
    int idInt;
    int size;
    
    
    public Line() {
        size = 0;
    }
    public Line(Queue<customer> customers) {
        this.customers = customers;
    }
    public Line(String id) {
        this.id = id;
        size = 0;
    }
    public Line(String id, Queue<customer> customers) {
        this.id = id;
        this.customers = customers;
    }
    
    
    
    public void addCustomer(customer c) {
        size++;
        customers.add(c);
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setId(int idInt) {
        this.idInt = idInt;
    }
    
    public Queue<customer> getCustomerQueue() {
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
        double tot = 0.0;
        Queue<customer> temp = customers;
        
        for(int i = 0; i < temp.size(); i++) {
            tot += temp.remove().getTimeToProcess();
        }
        
        return tot;
    }
    
    
}
