/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hal_interpreter;

/**
 *
 * Letzte Ändeung: 
 * - Änderung int -> float
 * 
 */
public class Acc {
    
    
    private float value=0; // Default Wert
    
    
    void setValue(float _val){
        this.value=_val;
    }
    
    float getValue(){
        return value;
    }
    
}
