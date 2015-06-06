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
public class OUT {
    
 
   private float value;
   IN []INS;
   OUT []OUTS;
   Buffer buf = new  Buffer();
  
  
    public OUT(){
        value=0;
  
        
    }
    
    void setnPrintValue(float val){
        this.value=val;
        System.out.println("Ergebniss: "+ value);
    }
    
    
    public void set_value(float val){
        this.value=val;
    }
    
    
      
  void putBuf (){
     
      buf.put(value);
      
  }
    
    Buffer retBuf(){
        return buf;
    }
    
    void putFloattoBuf(float value){
        buf.put(value);
    }
    
   
}
