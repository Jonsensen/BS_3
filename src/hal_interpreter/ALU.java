
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
   IN[] INS;
   OUT[]OUTS;
  
   
    /*
    Programm Memory und Register als Referenz holen. Arrays für IN/ OUT Schnittstellen erstellen und Füllen wie in HAL_Interpreter angegeben.
    */
    public ALU(ProgramMemory _ProgMem, Register _reg, boolean _debugMode,IN[] ins,OUT[] outs){
     ProgMem= _ProgMem;
     regs=_reg;
     debugMode=_debugMode;
     this.INS =ins;
     this.OUTS=outs;
     //this.INS = new IN[ins.length];
     //this.OUTS = new OUT[outs.length];
     INS =ins;
     OUTS = outs;
   
    }
    /*
    Aufrufen von ExecComm mit Jeweiligem Programm Counter (Registger 0) und inkrementieren von PC
    So lange durchlaufen, bis Befehl "STOP" erreicht ist
    */
   public void startExec(){
       System.out.println("Programm Start: ");
       boolean quit=false;
       int debugzaehler=0;
       while(!quit){
         //  System.out.println("In Startexec vor Befehl RO: "+regs.getregister(0));
         //  System.out.println("ListElement: "+ProgMem.getlistElement(regs.getregister(0)).getCommand());
           
            // Math.round um den Register Wert von PC nach int zu Casten 
            quit=ExecComm(ProgMem.getlistElement(Math.round(regs.getregister(0)))); 
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
           case "START": {break;} // Momentan ohen Funktion
           case "STOP" : {quithelper=true;break;}                                       
           case "OUT"  : {try{OUTS[_instruktion.getConstant()].setnPrintValue(aku.getValue());}
                          catch(ArrayIndexOutOfBoundsException e){System.err.println("Dieses OUT Objekt gibt es nicht !");}
           break;}       
           
           case "IN"   : { try{aku.setValue(INS[_instruktion.getConstant()].readFloatValue());}
                           catch(ArrayIndexOutOfBoundsException e ){System.err.println("Dieses IN Objekt gibt es nicht !"+_instruktion.getConstant());} 
           break;}        
           
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
                if (aku.getValue()<0){
                    regs.setregister(0, _instruktion.getConstant());
                if(debugMode){ System.out.println("Register 0: "+regs.getregister(0));}
                regs.decremCounter();}
                break;
            }
            
            case "JUMPPOS" :{// Springt zur ProgrammSpeicherAdresse (a) wenn aku positiven Wert hat
                if (aku.getValue()>=0){
                regs.setregister(0, _instruktion.getConstant());
                if(debugMode){ System.out.println("Register 0: "+_instruktion.getConstant());}
                regs.decremCounter();}
                break;
            }
           
            case "JUMPNULL" : {// Springt zur ProgrammSpeicherAdresse (a) wenn aku 0 ist
                if(aku.getValue()==0){
                    regs.setregister(0, _instruktion.getConstant());
                if(debugMode){ System.out.println("Register 0: "+regs.getregister(0));}
                regs.decremCounter();}
                break;
            }
            
            case "JUMP" : { // Springt zur ProgrammSpeicherAdresse (a)
                regs.setregister(0, _instruktion.getConstant());
                if(debugMode) {System.out.println("Register 0: "+regs.getregister(0));}
                regs.decremCounter();
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
