
import java.util.Queue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Anthony Vigil
 */
public class customer {
    String id;
    int idInt;
    Line line;
    double timeToProcess; //represents seconds that this user will take to order
    
    public customer(Line line) {
        this.line = line;
    }
    public customer(Line line, double timeToProcess) {
        this.line = line;
        this.timeToProcess = timeToProcess;
    }
    
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
}
