


package hal_interpreter;

/**
 *
 * @author debian
 */
public class IOConfig {
    
    /*
    vonHAL = Sendender HAL Baustein
    nachHAL = Empfangender HAL Baustein
    vonPort = Sendender Port 
    nachPort = Empfangender Port
    */
    
    private  int vonHAL ;
    private  int nachHAL;
    private  int vonPort;
    private  int nachPort;
    private boolean used = false;
   // private Buffer buffer;
    

    public IOConfig() {
    vonHAL=0;
    nachHAL=0;
    vonPort=0;
    nachPort=0;
    
    
    }

    /**
     * @return the vonHAL
     */
    public int getVonHAL() {
        return vonHAL;
    }

    /**
     * @param vonHAL the vonHAL to set
     */
    public void setVonHAL(int vonHAL) {
        this.vonHAL = vonHAL;
    }

    /**
     * @return the nachHAL
     */
    public int getNachHAL() {
        return nachHAL;
    }

    /**
     * @param nachHAL the nachHAL to set
     */
    public void setNachHAL(int nachHAL) {
        this.nachHAL = nachHAL;
    }

    /**
     * @return the vonPort
     */
    public int getVonPort() {
        return vonPort;
    }

    /**
     * @param vonPort the vonPort to set
     */
    public void setVonPort(int vonPort) {
        this.vonPort = vonPort;
    }

    /**
     * @return the nachPort
     */
    public int getNachPort() {
        return nachPort;
    }

    /**
     * @param nachPort the nachPort to set
     */
    public void setNachPort(int nachPort) {
        this.nachPort = nachPort;
    }
    
    
    public void setUsed(boolean isused){
        this.used = isused;
    }    
    
    public boolean isUsed(){
        return used;
    }
    
    public void printConf(){
        System.out.println("VON HAL  :"+vonHAL);
        System.out.println("VON PORT :"+vonPort);
        System.out.println("Nach HAL :"+nachHAL);
        System.out.println("NACH PORT :"+nachPort);
                
    }
    
   
    
}
