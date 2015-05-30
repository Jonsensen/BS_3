/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hal_interpreter;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author debian
 */




public class main {
    
    


    // "Sicherung" von Termin 3 - einfache Ausführung im Normlen Thread. Bei Benutzung rückgabe ergänzen !
   public void singleExec(String [] args){
       
         // Zum Aufruf des Programmes im Debug modus "-d" angeben !
        String defautFileName ="test.txt";
        boolean debugMode = false; 
     
 
        File input = new File(defautFileName);
              
        // Default namen ohne Debug Infos
        if (args.length==0){
        input = new File(defautFileName);
        }
        
        if (args.length==1){// Entweder andere Datei Ohne Debug Infos oder nur Debug Infos
            
          
            if (args[0].equals("-d")){// DefaultFileName mit Debug Infos
                debugMode=true;
                input = new File (defautFileName);
                }
            
            
            else{// andere Datei ohne Debug Infos
                input = new File(args[0]);
                }
            
        }
         
        
        //anderer Dateinamen und Debug Infos
        else if (args.length==2){
        input = new File (args[0]);
        debugMode=true;
        }
       
       
   }
    
    
    
    
     
    public static void main(String[] args) throws IOException {
     
        boolean debugMode = false;
        
        if(args[0].equals("-d")){
            debugMode=true;
        }
     
        /*
       Für nächste Aufgabe:   4 HAL Bausteine erstellen, 2 mittlere, einen für eingabe und einen für ausgabe. Von IO_Oberklasse erben. 
        implements Runnable, damit keine mehrfachvererbung. 
        
        */
        
        
        /*
        Auslesen der HALConf Datei und Erstellen der Jeweiligen HAL Bausteine mit jeweiligem Ausführbaren Programm
        */
        File Conf = new File("HALConf.txt");
        Scanner scanner = new Scanner (Conf);
        while (scanner.hasNextLine()){
            
            // ...
                
        }
        
        
        
        
        
        
      
        //HAL_Interpreter hal1 = new HAL_Interpreter(4, 4, input, debugMode); // params : anz. InIOs, anz. OutIOs, Dateiname, debugmode
        //HAL_Interpreter hal2 = new HAL_Interpreter(4, 4, input, debugMode);
        //HAL_Interpreter hal3 = new HAL_Interpreter(4, 4, input, debugMode);
        
       
        
        
        
        
        
        
      
    }
    
    
    
}
