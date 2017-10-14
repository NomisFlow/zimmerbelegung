package zimmerbelegung;

import java.util.LinkedList;

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
      
    public String sortiere(){
        for(Schueler s: alleSchueler){
            hinzufuegen(s,null);
        }
        return getBelegung();
    }
     
    private void hinzufuegen(Schueler s, Zimmer z){  
        if(getZimmerForSchueler(s) == null){
            Zimmer zimmer = z;
            if(z == null){
                zimmer = new Zimmer();
                alleZimmer.add(zimmer);
            }
            zimmer.addSchueler(s);
            for(Schueler fav : s.getFavorites()){
                hinzufuegen(fav,zimmer);
            }
        }
    }
    
    private Zimmer getZimmerForSchueler(Schueler s){
        for(Zimmer z: alleZimmer){
            if(z.istInZimmer(s)){
                return z;
            }
        }
        return null;
    }

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
}