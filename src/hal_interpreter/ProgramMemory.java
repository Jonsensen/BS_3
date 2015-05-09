package hal_interpreter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProgramMemory {
    
    ArrayList<listElement> list = new ArrayList<listElement>();
    Scanner s;
    String line ;
    
    public ProgramMemory(File programCode) throws IOException{
    
    list.clear();   
    
        try {
            // Auslesen aus einer Datei            
            BufferedReader r = new BufferedReader(new FileReader(programCode));
            
            /*
            Leskopf Steht am Anfang der Datei und wird nun Zeilenweise einlesen
            Solange wie Zeilen vorhanden(!=null).
            */
            line = null;           
            while((line = r.readLine())!=null){
                
                s = new Scanner(line);
               
                /*
                einzelne Zeilen aufteilen in Programzähler,Befehl und ggf. Konstante
                und in die Liste abspeichern
                (mit hilfe der Scanner funktion)
                */
                if(s.hasNext()){
                                   
                    int tmpProgrammCounter = s.nextInt();
                    String tmpCommand= s.next();
                    int tmpconstant;
                    
                    if(s.hasNext()){
                    tmpconstant= s.nextInt();   
                    }else tmpconstant =12;  // wenn keine konstante angebeben wird testhalb 12 abgespeichert
                   
                    /*
                    die einzelenen Werte in listen Obejekte abspeichern
                    */
                    listElement li = new listElement(tmpProgrammCounter,tmpCommand,tmpconstant); 
                    
                    list.add(li);
                    
                }               
             //System.out.println(line);           
            }
            
            } catch (FileNotFoundException ex) {
            System.out.println("Fehler beim einlesen der Datei");
        }    
    }
  
    public String getInstruktion(int choiceInstruktion){
    
  
        for(int i=0;i<list.size();i++){
        
        if(i==choiceInstruktion){
         System.out.println("Befehl: "+i);
        return list.get(i).getCommand();
        }
       
        }
    
    return "0";
    }  
}

class listElement{
    private int programmCounter;
    private String command;
    private int constant; 
    
    public listElement(int _programmCounter,String _command,int _constant){   
    this.programmCounter=_programmCounter;
    this.command=_command;
    this.constant=_constant;
    }   

    /**
     * @return the programmCounter
     */
    public int getProgrammCounter() {
        return programmCounter;
    }

    /**
     * @return the command
     */
    public String getCommand() {
        
        return command;
         
    }

    /**
     * @return the constant
     */
    public int getConstant() {
        return constant;
    }
    
    
}


