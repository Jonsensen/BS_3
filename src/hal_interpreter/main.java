/*
 TO DO : Schönhetitsänderungen: 
-IO Interface nocht als Klasse sondern als Interface 
- Schleifen, die Arrays komplett durchgehen durch Iteratoren ersetzen! 
 */

package hal_interpreter;

import java.io.File;
import java.io.IOException;


/**
 *
 * @author debian
 */


public class main {
    
   

    // "Sicherung" von Termin 3 - einfache Ausführung im Normlen Thread. Bei Benutzung rückgabe ergänzen !
    /*
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
    
    */
   
    public static void main(String[] args) throws IOException {
     
        boolean debugMode = false;
        
        if(args.length!=0&&args[0].equals("-d")){
            debugMode=true;
        }
        
        HAL_OS OS = new HAL_OS(debugMode);
        OS.initHALS();
        OS.startHALS();
     
      
    }
    
    
    
}
