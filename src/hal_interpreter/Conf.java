/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hal_interpreter;
import java.util.ArrayList;


/**
 Allgemeine Configuration mit allen Programmnamen und den einzelnen IO_configs
 */
public class Conf {
    
    
    private ArrayList<String> Programmnames= new ArrayList<>();
    private ArrayList<IOConfig> IO_configs = new ArrayList<>();
    
    
    public Conf(){
        
    }

    /**
     * @return the Programmnames
     */
    public ArrayList<String> getProgrammnames() {
        return Programmnames;
    }

    /**
     * @param Programmnames the Programmnames to set
     */
    public void setProgrammnames(ArrayList<String> Programmnames) {
        this.Programmnames = Programmnames;
    }

    /**
     * @return the IO_configs
     */
    public ArrayList<IOConfig> getIO_configs() {
        return IO_configs;
    }

    /**
     * @param IO_configs the IO_configs to set
     */
    public void setIO_configs(ArrayList<IOConfig> IO_configs) {
        this.IO_configs = IO_configs;
    }
    
    
    public void addIOConf(IOConfig conf){
        this.IO_configs.add(conf);
    }
    
    public void addProgName(String progname){
        this.Programmnames.add(progname);
    }
    
    
    IOConfig getIOconfVonHAl(int HALnummer){
        IOConfig emptyConf = new IOConfig();
        
        for (int i=0;i<IO_configs.size();i++){
            if (IO_configs.get(i).getVonHAL()==HALnummer){
                return IO_configs.get(i);
            }
        }
                
        System.err.println("IOConfig von HAL "+HALnummer+" konnte nicht gefunden werden !!!");
        return emptyConf;
    }
    
    
    IOConfig getIOconfNachHAL(int HALnummer){
        IOConfig emptyConf= new IOConfig();
        for(IOConfig conf : IO_configs){
            if(conf.getNachHAL()==HALnummer){
                return conf;
            }
        }
        System.err.println("IOConfig nach HAL "+HALnummer+" konnte nicht gefunden werden !!!");
        return emptyConf;
    }
    
}
