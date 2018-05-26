/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Henry Estberg
 */
public class RunLoop {
    
    public RunLoop (int l, int t) {
        this.lineNumber = l;
        this.tellerNumber = t;
    }
    
    private long tick;
    private int lineNumber;
    private int tellerNumber;

  
    
    public void run() {
        tick = 0;
        ArrayList<Teller> tellers = new ArrayList();
        ArrayList<Line> lines = new ArrayList();
        
        for(int k = 0; k < lineNumber; k++) {
            lines.add(new Line());
        }
        
        //The constructor requires the random generation for teller speed to be done before item generation
        //for(int l = 0; l < tellerNumber; l++) {
        //    tellers.add(new Teller());
        //}
        
        
        while(true) {
            
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(RunLoop.class.getName()).log(Level.SEVERE, null, ex);
            }    
            
            for(Teller tell : tellers) {
                if(tell.getTransTime() == 0){
                    //send the next customer in line to the teller
                }
            }
            /*
                Generate New customers
                Check if transaction is complete
                
            */  
            tick += 1;
        
        }
    }
}
