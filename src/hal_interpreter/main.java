/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hal_interpreter;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.ArrayList;


/**
 *
 * @author debian
 */




public class main {
    
    
static ArrayList<String>  readConfig(){
 ArrayList<String>ProgrammfileNames = new ArrayList<String>();
       try {
		BufferedReader in = new BufferedReader(new FileReader("HalConf.txt"));
		String zeile = null;
               
		while ((zeile = in.readLine()) != null) {
			
                    if (!zeile.equals("#HAL_Prozessoren :")){
                            // System.out.println("Gelesene Zeile: " + zeile);
                             String tmp = zeile;
                             String [] splittLine =zeile.split(" ");
                            // System.out.println("Einzelne : "+Arrays.toString(splittLine));
                        
                       //     System.out.println(splittLine[1]);
                            ProgrammfileNames.add(splittLine[1]);
                    }    
                        
                   }
                
                
	} catch (IOException e) {
		e.printStackTrace();
	}
        
    
     return ProgrammfileNames;
}
    

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
        
        if(args.length!=0&&args[0].equals("-d")){
            debugMode=true;
        }
        
        // Namen aller Programme für die Einzelnen HAls
    ArrayList<String> ProgrammNames = readConfig();
        
    // Hier sind Alle HAl Objekte Gepeichert
    ArrayList<HAL_Interpreter> HALS = new ArrayList<HAL_Interpreter>();
    
    // Hal init und in HAlS AraayList + Starten der Threads
    for (int i=0;i<ProgrammNames.size();i++){
        System.out.println(ProgrammNames.get(i));
        File inputFile = new File(ProgrammNames.get(i));
        HAL_Interpreter tmpHAL = new HAL_Interpreter(4, 4, inputFile, debugMode);
        HALS.add(tmpHAL);
        tmpHAL.run();
        
    }
    
        
        /*
       Für nächste Aufgabe:   4 HAL Bausteine erstellen, 2 mittlere, einen für eingabe und einen für ausgabe. Von IO_Oberklasse erben. 
        implements Runnable, damit keine mehrfachvererbung. 
        
        */
        
        
 
        
        
        
      
    }
    
    
    
}
