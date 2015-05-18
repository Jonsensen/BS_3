package hal_interpreter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class HAL_Interpreter {

  
    
    
    public static void main(String[] args) throws IOException {
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
            
        ProgramMemory ProgrammMem = new ProgramMemory(input);
        Register regs = new Register();
        ALU alu = new ALU(ProgrammMem,regs,debugMode);
       // System.out.println("Ausgabe der Instruktion in Main: "+ProgrammMem.getInstruktion(0));
        alu.startExec();
        
    }
    
}

/* ProgrammMemory Testen
        File input = new File("test1.txt");
        ProgramMemory test = new ProgramMemory(input);
        System.out.println(test.getInstruktion(12));

*/

/* Register testen

 Register r= new Register();
        
        r.setregister(0, 100);
        r.setregister(3, 130);
        r.setregister(6, 180);
        r.setregister(9, 200);
        r.setregister(20, 1); //muss fehlschlagen
        
        
        for(int i=0;i<10;i++){
        
        System.out.println("Register:"+i+":"+r.getregister(i));
        
        }
*/