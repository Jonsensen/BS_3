/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hal_interpreter;

import java.util.Scanner;

/**
 *
 * @author debian
 */
public class IN {
   
    private float value; 
    private Buffer buf;
    
    
    public IN(){
        value=0;
        
        }
 
    
    float readFloatValue(){
        String tmpString;
        Scanner cin = new Scanner(System.in);
        System.out.println("> ");
       tmpString = cin.nextLine();
       value = Integer.parseInt(tmpString);
       return value;
    }
    
  float getValue(){
      return value;
  }  


 
 
 void setBuf(Buffer buf){
     this.buf=buf;
 }
 
 
 float getBuf(){
     return buf.get();
 }
  

 
 
}
