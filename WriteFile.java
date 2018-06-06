
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger; 

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Henry Estberg
 */
public class WriteFile {
    
    private String filePath;
    private boolean append = true;
    PrintWriter writer = null;
    
    public WriteFile(String path, boolean ifAppend) {
        this.filePath = path;
        this.append = ifAppend;
        
    }
    
    public void write(String text) {
        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(filePath, append)));
            writer.println(text);
        } catch (IOException ex) {
            Logger.getLogger(WriteFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
            }
        }

    }
    
}
