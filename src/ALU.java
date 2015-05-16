/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    So lange durchlaufen, wie es Befehszeilen im ProgrammMemory gibt.
    */
   public void startExec(){
  // Scheifenbedingung Prüfen. Schleife soll laufen bis Ende der HAL(.txt) Datei.
       //for(int i = 0;!ProgMem.s.hasNext();i++){
       for (int i =0;i<ProgMem.getNumberInstruktionLines();i++){
           System.out.println("Schleife in StartExec: "+i);
        ExecComm(ProgMem.getlistElement(regs.getregister(0)));
        regs.incremCounter();
       }
       
   }
    
     
   private void ExecComm(listElement _instruktion){
      
       
          if(debugMode){ // zusätzliche Informationen bei Vor jedem Befehl
               System.out.println("Vor Ausführung des Befehls: "+_instruktion.getWhleCommand());
               System.out.println("Wert des Akkus: "+aku.getValue());
           }
          
       
       switch(_instruktion.getCommand()){
           case "START": {System.out.println("Programm wurde gestartet!");break;}
       
           case "STOP" : {System.out.println("Programm wird beendet !");break;}         // Nicht fertig
           case "OUT"  : {break;}                                                       // Nicht fertig
           case "IN"   :{break;}                                                        //Nicht fertig
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
               break;
           }
           
            /*
            Jumps können in ein Case Zusammengefasst werden?!
            */
            case "JUMPNEG" :{// Springt zur ProgrammSPeicherAdresse (a) wenn aku negativen Wert hat
                if (aku.getValue()<0)
                    regs.setregister(0, _instruktion.getConstant());
                break;
            }
            
            case "JMPPOS" :{// Springt zur ProgrammSpeicherAdresse (a) wenn aku positiven Wert hat
                if (aku.getValue()>=0)
                regs.setregister(0, _instruktion.getConstant());
                break;
            }
           
            case "JMPNULL" : {// Springt zur ProgrammSpeicherAdresse (a) wenn aku positiven Wert hat
                if(aku.getValue()==0)
                    regs.setregister(0, _instruktion.getConstant());
                break;
            }
            
            case "JMP" : { // Springt zur ProgrammSpeicherAdresse (a)
                regs.setregister(0, _instruktion.getConstant());
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
            
       }// Ende Switch
       
       
        if(debugMode){ // zusätzliche Informationen bei Nach jedem Befehl
               System.out.println("Vor Ausführung des Befehls: "+_instruktion.getWhleCommand());
               System.out.println("Wert des Akkus: "+aku.getValue());
           }
 
       
    } // Ende Methode
    
     
    
} // Ende Klasse
