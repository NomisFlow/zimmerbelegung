package zimmerbelegung;

import java.io.BufferedReader;
import java.util.LinkedList;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author simonwolf
 * @author simonbothe
 */

public class Zimmerbelegung {
    private LinkedList<Schueler> alleSchueler;
    private LinkedList<Zimmer> alleZimmer; 
    
    public static void main(String[] args){
        Zimmerbelegung z = new Zimmerbelegung("zimmerbelegung1");
        
    }
        
    
    public Zimmerbelegung(String filename){
        alleSchueler = new LinkedList<Schueler>();
        alleZimmer = new LinkedList<Zimmer>();
        einlesen(filename);
        sortiere();
        getBelegung();
    }
      
    //sortiert zuerst alle Schueler nach likes und fuehrt danach die getBelegung() Methode aus
    public String sortiere(){
        for(Schueler s: alleSchueler){
            hinzufuegen(s,null);
        }
        return getBelegung();
    }
     
    private void hinzufuegen(Schueler s, Zimmer z){  
        //Ueberprueft, ob die Person bereits zugeordnet wurde
        if(getZimmerForSchueler(s) == null){
            Zimmer zimmer = z;
            if(z == null){
                //Sollte kein Zimmer angegeben worden sein, wird ein neues erstellt
                zimmer = new Zimmer();
                alleZimmer.add(zimmer);
            }
            zimmer.addSchueler(s);
            //Geht die gesammte favorites-Liste durch um diese in das gleiche Zimmer hinzuzufuegen
            for(Schueler fav : s.getFavorites()){
                hinzufuegen(fav,zimmer);
            }
        }
    }
    
    //Hilfs-Methode um das Zimmer fuer einen bestimmten Schueler zu erhalten. Ist der Schueler noch nicht
    //zugeordnet, dann wird null zurueckgegeben
    private Zimmer getZimmerForSchueler(Schueler s){
        for(Zimmer z: alleZimmer){
            if(z.istInZimmer(s)){
                return z;
            }
        }
        return null;
    }

    //Ueberprueft, ob die Zimmerkonstellation auch nach Dislikes moeglich ist und gibt danach das Ergebniss als String zurueck
    private String getBelegung(){
        for(Zimmer z: alleZimmer){
            LinkedList<Schueler> belegungZimmer = z.getBelegung();
            for(Schueler s: belegungZimmer){
                for(Schueler s1: s.getDislikes()){
                    if(z.istInZimmer(s1)){
                        return "Keine Einteilung möglich";
                    }
                }
            }
        }
        String belegung = "";
        for(Zimmer z: alleZimmer){
            belegung = belegung + z.getBelegungName();
        }
        belegungInDatei(belegung);
        System.out.println(belegung);
        return belegung;
    }
    

    private void einlesen(String filename){ 
        Path file =  Paths.get("Files/" + filename +".txt");
        Schueler schueler;
        LinkedList<Schueler> s = new LinkedList<Schueler>();
        
        //Jede vierte Zeile der Datei wird ausgelesen. Startpunkt variiert, je nachdem welche Information benötigt wird.
        List<String> schuelerNamen = getLines(file, 0); 
        List<String> positivListen = getLines(file, 1);
        List<String> negativListen = getLines(file,2);
        //Initialisiere alle Schüler 
        for(String e: schuelerNamen){
            schueler = new Schueler(e);
            alleSchueler.add(schueler);  
        }
        
        int i = 0;  //Zählvariable um durch Liste aller Schüler zu iterieren.
        //Füge Positiv-/Negativliste zu jedem Schüler hinzu
        for(String e: positivListen){
            String[] strArr = e.split(" "); //Array nötig um split-Methode zu benutzen
            ArrayList<String> parts = new ArrayList<String>(Arrays.asList(strArr)); //jedoch restliche Handhabung mit einer Liste einfacher
            parts.remove(0); //Pluszeichen wird entfernt
            
            //jeder Eintrag in 'parts' wird mit dem Namen jeden vorhandenen Schülers verglichen
            for(String str: parts){ 
                for(Schueler sch: alleSchueler){
                    if(sch.getName().equals(str)){
                        alleSchueler.get(i).addFavorite(sch);
                    }
                }
            }
            i++;  
        }
        i = 0;
        
        for(String e: negativListen){
            String[] strArr = e.split(" ");
            ArrayList<String> parts = new ArrayList<String>(Arrays.asList(strArr));
            parts.remove(0);   
            for(String str: parts){
                for(Schueler sch: alleSchueler){
                    if(sch.getName().equals(str)){
                        alleSchueler.get(i).addDisliked(sch);
                    }
                }
            }
            i++;    
        } 
    } 
    
    private List<String> getLines(Path file, int l){
        Charset charset = Charset.forName("US-ASCII");
        LinkedList<String> list = new LinkedList<String>(); 
        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            List<String> allEntries = Files.readAllLines(Paths.get(file.toString()));
            for(int i = l; i < allEntries.size(); i +=4){
                list.add(allEntries.get(i));   
            }
            return list;
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }   
        return null;
    }
    
    private void belegungInDatei(String belegung){
        
    }
}