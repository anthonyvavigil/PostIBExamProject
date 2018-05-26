/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package postibexamproject;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Henry Estberg
 */
public class RunLoop {
    
    public RunLoop(int lines){
        this.lines = lines;
    }
    
    private long tick;
    private int lines;
    
    public void run() {
        tick = 0;
        ArrayList<Teller> tellers = new ArrayList();
        
        
        
        while(true) {
            
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(RunLoop.class.getName()).log(Level.SEVERE, null, ex);
            }    
            
            for(Teller tell : tellers) {
                if(tell.getTransTime() == 0){
                    
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
