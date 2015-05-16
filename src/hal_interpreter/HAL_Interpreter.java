package hal_interpreter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/*
NEW:
-listelement.wholecommand ergänzt
- Übergabe der Auszuführenden Datei in Console möglich 
-Acc erstellt
-ALU erstellt + Konstruktor + ExecComm + startExec
- ProgrammMemory.getlistElement hinzugefügt
*/
public class HAL_Interpreter {

  
    
    
    public static void main(String[] args) throws IOException {
       
        String defautFileName ="test.txt";
        boolean debugMode = false;
        File input = new File(defautFileName);
        
        // Funktioniert ohne debug -> erweitern 
       // Übergabe des Programmnamens im ersten Parameter
        if(args.length==1){
            input = new File(args[0]);
            System.out.println("Einhabedatei:"+args[0]);
        }
         // Ohne Parameter: default Datei setzen
        else{
            input = new File("test.txt");  // Test Datein = test.txt,test1.txt 
        }
        
        
        
       
        ProgramMemory ProgrammMem = new ProgramMemory(input);
        Register regs = new Register();
        ALU alu = new ALU(ProgrammMem,regs,debugMode);
        System.out.println("Ausgabe der Instruktion in Main: "+ProgrammMem.getInstruktion(3));
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