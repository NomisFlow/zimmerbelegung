package zimmerbelegung;

import java.util.LinkedList;

/**
 * @author simonwolf
 * @author simonbothe
 */

class Zimmer {
    private LinkedList <Schueler> belegung;
    
    public Zimmer(){
        belegung = new LinkedList<Schueler>();
    }
    
    public void addSchueler(Schueler s){
        belegung.add(s);
    }
    
    public Zimmer getZimmer(){
        return this;
    }
    
    public LinkedList getBelegung(){
        return belegung;
    }
    
    //Gibt eine String-Liste mit den Namen der zugeordneten Schuelerinnen zurueck
    public LinkedList getBelegungName(){
        LinkedList<String> belegung = new LinkedList<String>();
        for(Schueler s: this.belegung){
            belegung.add(s.getName());
        }
        return belegung;
    }
    
    //Ueberprueft ob sich der uebergebende Schueler in diesem Zimmer befindet 
    public boolean istInZimmer(Schueler s){
        for(Schueler schueler: belegung){
            if(s == schueler)
                return true;
        }
        return false;
    }
    
}
