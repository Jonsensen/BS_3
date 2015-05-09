package hal_interpreter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class HAL_Interpreter {

    
    
    
    public static void main(String[] args) throws IOException {
       
        File input = new File("test1.txt");  // Test Datein = test.txt,test1.txt
        ProgramMemory test = new ProgramMemory(input);
        System.out.println(test.getInstruktion(12));
        
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