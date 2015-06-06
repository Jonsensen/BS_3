/*
 Verwaltet die einzelenen HAL Bausteine
 */

package hal_interpreter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author debian
 */
public class HAL_OS {
    
    Conf Config = new Conf(); 
    private boolean debugMode=false;
    
    // Hier sind alle Programmnamen für die jeweiligen HALS gespeichert
    private ArrayList<String> ProgrammNames = new ArrayList<>();
    
    // Hier sind alle HAL Objekte gespeichert.
    ArrayList<HAL_Interpreter> HALS = new ArrayList<>();
     
    public HAL_OS(boolean debugMode){
        this.debugMode=debugMode;
        
        this.Config=readConfig();
        ProgrammNames=Config.getProgrammnames();
         if (debugMode){
        printStartInfos();
         }
        
    }// Ende Konstruktor
    

    /*
     initHALS:
    
    -Erstellt HAL Interpreter anhand der Programmnames größe
    
    Es gibt 3 Typen HAl BAusteine:
    
    1:  Vor der Schleife wird das Erste HAL Objekt erstellt und "First" zugewiesen, damit der HAL Baustein 
    eine Eingabe liest und diese Weitergibt
    
    2: In der Schleife werden alle weiteren mittleren ("Normal") HALS erstellt, welche von jeweiligem IN objekt einlesen, berechnenn 
    und an jeweiliges OUT Objekt weitergeben 
    
    
    3: Nach der Schleife wird das letzte HAL Objekt erstellt, welches über jeweiligen IN einliest allerdings Ergebniss nicht weiter gibt, sondern
    das Ergebniss mittels Sys.out ausgibt. 
    
    */
    
    void initHALS(){
        int HAL_ID=0;
      try{  
         // Hal init und in HAlS AraayList 
          File inputFile1 = new File(ProgrammNames.get(0));
          HAL_Interpreter tmpHAL1 = new HAL_Interpreter(4, 4, inputFile1, debugMode, "First", HAL_ID++);
          // Config2(ausgabeconfig) bekommt enstsprechende Config.
          tmpHAL1.setConf(2, Config.getIOconfVonHAl(0));
          HALS.add(tmpHAL1);
          /*
          Schleife soll HAL objekte erstellen von allen Programmnames außer dem ersten und dem letzten Element (für alle "Mittleren" HALS)!
          */
    for (int i=1;i<(ProgrammNames.size()-1);i++){ 
        File inputFile2 = new File(ProgrammNames.get(i));
            
        // Erstellen des HAL Interpreters mit jeweiligen inputFiles 
        HAL_Interpreter tmpHAL2 = new HAL_Interpreter(4, 4, inputFile2, debugMode,"Normal",HAL_ID++);
        
        // Logik fürs setzen der Config des Jeweiligen HALS.
       tmpHAL2.setConf(1,Config.getIOconfNachHAL(HAL_ID-1));
       tmpHAL2.setConf(2, Config.getIOconfVonHAl(HAL_ID-1));
        HALS.add(tmpHAL2); // Hinzufügen des HAL Bausteins in HAL-Liste 
      } // Ende for ProgrammNames
    
    File inputFile3 = new File(ProgrammNames.get(ProgrammNames.size()-1));
    HAL_Interpreter tmpHAL3= new HAL_Interpreter(4, 4, inputFile3, debugMode, "Last",HAL_ID);
    // Setzt jeweilige Config
    tmpHAL3.setConf(1, Config.getIOconfNachHAL(HAL_ID));
    HALS.add(tmpHAL3);
    
 
    // Setzen der HAL Liste für jeden HAL !!!
    for (HAL_Interpreter schleifenHAL : HALS){
        schleifenHAL.setHalList(HALS);
    }
    
    }// Ende try
    catch(IOException e){
            System.out.println("Feher beim erstellen / initialisieren der HAL_Interpreter");}
        
    }// Ende initHALS
        
    
    
    /*
    Liest HALConf.txt aus und speichert Programmnamen und HAL-Verbindungen
    */
final Conf  readConfig(){ 
    boolean hasmoreHals=true;
    Conf Configuration = new Conf();
    ArrayList<String>ConfigLines = new ArrayList<>();
       try {
		BufferedReader in = new BufferedReader(new FileReader("HalConf.txt"));
		String zeile = null;
               // Einlesen der HalConf.txt datei und Programmnamen in Conf::Programmnames schreiben
		while ((zeile = in.readLine()) != null) {
			
                     if(zeile.equals("#HAL-Verbindungen:")){
                            hasmoreHals=false;
                        }
                    if ((!zeile.equals("#HAL_Prozessoren :"))&&hasmoreHals){
                      
                        if(debugMode){
                             System.out.println("Gelesene Zeile: " + zeile); 
                        }
                             String tmp = zeile;
                             String [] splittLine =zeile.split(" ");
                    
                             Configuration.addProgName(splittLine[1]);
                    }    
                    
                    if((!hasmoreHals)&&!zeile.equals("#HAL-Verbindungen:")){
                      String tmp = zeile;
                        ConfigLines.add(tmp);
                    }
                        
                   }
                
                
	} catch (IOException e) {
		e.printStackTrace();
	}
        
    
       // Einzelenen ConfigLines "Aufdröseln" und in tmpIOConf setzen
       // Anschließend tmpIOConf in Conf::IOConfigs via addIOConf einfügen 
     
       for(String confLine : ConfigLines){
        
           String tmp = confLine;
           String[]splitt =confLine.split(":");
           IOConfig tmpIOConf = new IOConfig();
           String[] splitt2 = splitt[1].split(">");
          
           // Setzen in tmpConf: 
           tmpIOConf.setVonHAL(Integer.parseInt(splitt[0]));
           tmpIOConf.setVonPort(Integer.parseInt(splitt2[0]));
           tmpIOConf.setNachHAL(Integer.parseInt(splitt2[1]));
           tmpIOConf.setNachPort(Integer.parseInt(splitt[2]));
           
           Configuration.addIOConf(tmpIOConf);
       }
       
     return Configuration;
}
    
    
/*
Starten Aller HAL Objekte -> führt methode HAl_Interpreter::run aus. 
*/
void startHALS(){
        for (HAL_Interpreter HALS1 : HALS) {
            HALS1.start();
        }
}
    
   
final void printStartInfos(){
    
            System.out.println("Folgende Programm Namen wurden gelesen : ");
        for (int i =0;i<Config.getProgrammnames().size();i++){
            System.out.println(Config.getProgrammnames().get(i));
        }
        
             System.out.println("Folgende Verbindungen wurden gelesen : ");
            for (IOConfig config : Config.getIO_configs()){
                config.printConf();
                 System.out.println("----");
            }
    
}

    
    
    
    
}
