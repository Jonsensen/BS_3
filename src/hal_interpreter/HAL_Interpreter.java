package hal_interpreter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class HAL_Interpreter extends Thread{

   private ALU alu;
  
   public HAL_Interpreter(){
   
   }
   
   
    // Konstruktor des HAl Interpreters - zusammensetzen der Bestandsteile
    public HAL_Interpreter(int anzINs, int anzOUTs,File input,boolean debug)throws IOException{
    
        
    ProgramMemory ProgrammMem = new ProgramMemory(input);
    
    Register regs = new Register();
        
        IN[] INS = new IN[anzINs]; 
        OUT[] OUTS = new OUT[anzOUTs];
        
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
      alu.startExec();
       // Hier auszuführender Code
        
    }
    

 
    
    
}
