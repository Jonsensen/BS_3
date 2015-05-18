
package hal_interpreter;
/**
 *
 * @author debian
 */
public class ALU {
   
   ProgramMemory ProgMem; 
   Register      regs;
    Acc aku= new Acc();
    boolean debugMode = false;
   
    /*
    Programm Memory und Register als Referenz holen (In Java werden Objekte immer als Referenz übergeben!)
    */
    public ALU(ProgramMemory _ProgMem, Register _reg, boolean _debugMode){
     ProgMem= _ProgMem;
     regs=_reg;
     debugMode=_debugMode;
    }
    
    
    /*
    Aufrufen von ExecComm mit Jeweiligem Programm Counter (Registger 0) und inkrementieren von PC
    So lange durchlaufen, bis Befehl "STOP" erreicht ist
    */
   public void startExec(){
       
       boolean quit=false;
       int debugzaehler=0;
       while(!quit){
            quit=ExecComm(ProgMem.getlistElement(regs.getregister(0)));
            regs.incremCounter();
       }
       System.out.println("Programm wurde beendet");
       
   }
    
   
   private boolean ExecComm(listElement _instruktion){
      
       boolean quithelper=false;
       
          if(debugMode){ // zusätzliche Informationen bei Vor jedem Befehl
               System.out.println("***** Vor Ausführung des Befehls: "+_instruktion.getWhleCommand());
               System.out.println("Wert des Akkus: "+aku.getValue());
               System.out.println("*****");
           }
          
       
       switch(_instruktion.getCommand()){
           case "START": {System.out.println("Programm wurde gestartet!");break;}
           case "STOP" : {quithelper=true;break;}                                       
           case "OUT"  : {System.out.println("Noch nicht Implementiert");break;}        // Nicht fertig
           case "IN"   : {System.out.println("Noch nicht Implementiert");break;}        //Nicht fertig
           case "LOAD" : { // Läd Inhalt von Register (r) in aku
               aku.setValue(regs.getregister(_instruktion.getConstant()));
               break;
           }
           case "LOADNUM" : { // Läd Konstante in aku
               aku.setValue(_instruktion.getConstant());
               break;
           } 
           
            case "STORE" : { // Speichert Inhalt von aku in Register (r).
               regs.setregister(_instruktion.getConstant(),aku.getValue());
               if(debugMode) System.out.println("Register "+_instruktion.getConstant()+": "+regs.getregister(_instruktion.getConstant()));
               break;
           }
           
            /*
            Jumps können in ein Case Zusammengefasst werden?!
            */
            case "JUMPNEG" :{// Springt zur ProgrammSPeicherAdresse (a) wenn aku negativen Wert hat
                if (aku.getValue()<0)
                    regs.setregister(0, _instruktion.getConstant());
                if(debugMode) System.out.println("Register 0: "+regs.getregister(0));
                break;
            }
            
            case "JMPPOS" :{// Springt zur ProgrammSpeicherAdresse (a) wenn aku positiven Wert hat
                if (aku.getValue()>=0)
                regs.setregister(0, _instruktion.getConstant());
                if(debugMode) System.out.println("Register 0: "+regs.getregister(0)); 
                break;
            }
           
            case "JMPNULL" : {// Springt zur ProgrammSpeicherAdresse (a) wenn aku 0 ist
                if(aku.getValue()==0)
                    regs.setregister(0, _instruktion.getConstant());
                if(debugMode) System.out.println("Register 0: "+regs.getregister(0));
                break;
            }
            
            case "JMP" : { // Springt zur ProgrammSpeicherAdresse (a)
                regs.setregister(0, _instruktion.getConstant());
                if(debugMode) System.out.println("Register 0: "+regs.getregister(0));
                break;
            }
            
            case "ADD": {//  aku = aku + Register (a)
                aku.setValue(aku.getValue()+regs.getregister(_instruktion.getConstant()));
                break;
            }
            
            case "ADDNUM": {// aku = aku + Konstante (r) 
                aku.setValue(aku.getValue()+_instruktion.getConstant());
                break;
            }
            
            case "SUB" : { // aku = aku - register (a)
                aku.setValue(aku.getValue()-regs.getregister(_instruktion.getConstant()));
            break;
            }
            
            case "MUL" : {// aku = aku * register (a)
                aku.setValue(aku.getValue()*regs.getregister(_instruktion.getConstant()));
                break;
            }
            
            case "DIV": { // aku = aku/register (a)
            aku.setValue(aku.getValue()/regs.getregister(_instruktion.getConstant()));
            break;
            }
            
            case "SUBNUM": {// aku = aku -Konstante (r)
                aku.setValue(aku.getValue()-_instruktion.getConstant());
                break;
            }
            
            case "MULNUM":{// aku = aku * Konstante (r)
            aku.setValue(aku.getValue()+_instruktion.getConstant());
            break;
            }
            
            case "DIVNUM": {// aku = aku / Konstante (r)
            aku.setValue(aku.getValue()/_instruktion.getConstant());
            break;
            }
            default : {System.out.println(_instruktion.getCommand()+" ist kein zulässiger Befehl! - Beenden des Programmes");}
            
       }// Ende Switch
       
       
        if(debugMode){ // zusätzliche Informationen bei Nach jedem Befehl
               System.out.println("***** Nach Ausführung : " );
               System.out.println("Wert des Akkus: "+aku.getValue());
               System.out.println("*****");
           }
 
        if(quithelper)
            return true;
        
       return false;
        
    } // Ende Methode
    
     
    
} // Ende Klasse
