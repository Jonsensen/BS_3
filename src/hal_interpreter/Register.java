package hal_interpreter;

/*
Register 0 = Programm Counter


*/


public class Register {
    int register[]= new int[10];
    
    public Register(){
    
        for (int i = 0 ;i<register.length;i++){
        
        register[i]=0;
        }
           
    }
    
    public void setregister(int choiceRegister,int setValue){
    
        for (int a = 0 ;a<register.length;a++){
                      
            if(a==choiceRegister){ 
                
                register[a]=setValue;
                System.out.println("Register:("+a+")changed");
            }              
        }      
    }       
    
   
    
    public int getregister(int choiceRegister){
    
        for (int b = 0 ;b<register.length;b++){
        
            if(b==choiceRegister){
        
            return register[b];
        
            }   
        } 
        return 0;
    }
    
    
    
    public void incremCounter(){
    register[0]++;
}
    
    
}


