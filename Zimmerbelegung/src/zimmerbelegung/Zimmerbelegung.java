package zimmerbelegung;

import java.io.BufferedReader;
import java.util.LinkedList;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author simonwolf
 * @author simonbothe
 */

public class Zimmerbelegung {
    private LinkedList<Schueler> alleSchueler;
    private LinkedList<Zimmer> alleZimmer;     
    
    public Zimmerbelegung(LinkedList<Schueler> s){ 
        alleSchueler = s;
        alleZimmer = new LinkedList<Zimmer>();
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
        return belegung;
    }
    
    public LinkedList getAlleSchueler(){
        return alleSchueler;
    }
    private void einlesen(String filename){
        
    }
}