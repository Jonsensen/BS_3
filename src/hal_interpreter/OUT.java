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
    
   private int _value;
   
   
    public OUT(){
        
    }
    
    void setnPrintValue(int val){
        this._value=val;
        System.out.println("Ausgabe: "+ _value);
    }
    
    
    
}
