package hal_interpreter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class HAL_Interpreter extends Thread{
   int HAL_ID=0;
   private ALU alu;
   // conf1 = Linke Seite (Empfänger Seite)
   // conf2 = Rechte Seite (Sender Seite)
   private IOConfig conf1;
   private IOConfig conf2;
   boolean debug;
   
   // type : First(cin), Last(cout), Normal(alle in der Mitte,welche Daten von IN nach OUT schieben)
  private String type; 
  
  // Arrays der IN / OUT Objekte 
  private IN[] INS;
  private OUT[] OUTS;
  
 
  
  /* Array mit Zeiger auf die anderen HAL_Interpreter
    Muss Nach erstellen Aller HAL_Interpreter gesetzt werden !!!
  */
   private ArrayList <HAL_Interpreter> HalList;
 
   
    // Konstruktor des HAl Interpreters - zusammensetzen der Bestandsteile
    public HAL_Interpreter(int anzINs, int anzOUTs,File input,boolean deb,String Type, int HAL_id)throws IOException{
    
       // Erster , Letzter oder Mittlerer(Normal) - unterscheidung siehe Run. 
        this.type=Type;
        this.HAL_ID=HAL_id;
        ProgramMemory ProgrammMem = new ProgramMemory(input);
        this.debug=deb;
    
        Register regs = new Register();
        
         INS = new IN[anzINs]; 
         OUTS = new OUT[anzOUTs];
        
        // Init INPUT IO
        for (int i=0;i<anzINs;i++){
            INS[i]= new IN();
        }
        // Init OUTPUT IO
        for (int i=0;i<anzOUTs;i++){
            OUTS[i]= new OUT();
        }
        
         alu = new ALU(ProgrammMem,regs,debug,INS,OUTS);
        
        }

   // Erzeugen des Neuen Threads
    public void run() {
        // Ausführen des Jeweiligen HAL Codes
        alu.startExec();
      
      // Evtl. ändern in switch case   ?!
        if (type.equals("First")){
            
            float val = INS[0].readFloatValue();
             if(val<0){
                 System.out.println("Negative Zahl wurde eingegeben ! Ausgabe und Beenden !");
                    OUTS[3].setnPrintValue(val);
                    this.stop(); // Was ist gemeint mit Stoppe ?! (stop sollte eigentlich nie genutzt werden)
                }
            val = quad(val);
            // Setzen des OUT Portes
            OUTS[conf2.getVonPort()].putFloattoBuf(val);
            if(debug){
            System.out.println(val+" wurde von Port "+conf2.getVonPort()+" gesendet!"); 
            }
           
        }// Ende if -first
        
        else if (type.equals("Last")){
            // HAL Soll Zahl von jewiligem HAL / Port lesen , quadrieren und am Bildschrim ausgeben
            if(conf1.getNachHAL()==HAL_ID){
                // Buffer setzen von Entsprechendem HAL Baustein auf entsprechendem Port: 
                INS[conf1.getNachPort()].setBuf(HalList.get(conf1.getVonHAL()).getOUT(conf1.getVonPort()).retBuf());
                float val= INS[conf1.getNachPort()].getBuf();
                val = quad(val);
                System.out.println("Ausgabe an HAL_ID:"+HAL_ID+" : "+val);
             }
        }
        
        else if (type.equals("Normal")){
            
           // Wenn HAL ID == angesprochener HAl -> Eigener Buffer = Sende Buffer von entsprechendem Port auf entsprechendem HAL Baustein. 
            
            if(conf1.getNachHAL()==HAL_ID){
           INS[conf1.getNachPort()].setBuf(HalList.get(conf1.getVonHAL()).getOUT(conf1.getVonPort()).retBuf());
           
                  if(debug){
                System.out.println("Buffer von HAL_ID :"+HAL_ID+" Port: "+conf1.getNachPort()+" wird ersetzt durch HAL_ID: "+conf1.getVonHAL()+" Port: "+conf1.getVonPort()); // Testausgabe
                   }
                  
                float value=INS[conf1.getNachPort()].getBuf();
                value = quad(value);
                OUTS[conf2.getVonPort()].putFloattoBuf(value);
                
                if(debug){
                System.out.println(value+" wurde von HAL_ID:"+HAL_ID+" von Port:"+conf2.getVonPort()+" gesendet"); // Testausgabe
                }
            }// Ende if HAL_ID richtig..
           
           
        } // Ende "normal"
        
        
        
    }// Ende run 
        
    
    
    /*
    Setzt Die liste der anderen HAl Komponenten. 
    MUSS nach erstellung aller anderen gesetzt werden.
    */
     void setHalList(ArrayList<HAL_Interpreter> Hals){
        this.HalList=Hals;
    }

 
    public float quad(float val){
        float erg = (val*val);
        return erg;
    }
    
    
    IN getIN(int numberIN ){
        
        return INS[numberIN];
    }
    
    OUT getOUT(int numberOUT){
        return OUTS[numberOUT];
    }
    
    
    IOConfig getConf(int oneortwo){
      
        if(oneortwo==1){
            return conf1;
        }
        else 
            return conf2;
    }
    
    boolean isConfUsed(int oneortwo){
        if(oneortwo==1){
            return conf1.isUsed();
        }
        else 
            return conf2.isUsed();
    }
    
    
   public void setConf(int oneorTwo, IOConfig conf){
       if(oneorTwo==1){
           this.conf1=conf;
       }
   
       else if(oneorTwo==2){
           this.conf2=conf;
       }
       
       else{
           System.err.println("Gewünschte Configuration konnte nicht gesetzt werden !");
       }
   }
   
    
    
}

