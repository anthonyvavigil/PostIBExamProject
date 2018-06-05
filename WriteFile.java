
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
    BufferedWriter writer = null;
    
    public WriteFile(String path, boolean ifAppend) {
        this.filePath = path;
        this.append = ifAppend;
        
    }
    
    public void write(String text) {
        try {
            writer = new BufferedWriter(new FileWriter(filePath, append));
            writer.write(text);
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
