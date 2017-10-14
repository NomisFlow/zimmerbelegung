package zimmerbelegung;

import java.util.LinkedList;

/**
 * @author simonwolf
 * @author simonbothe
 */

class Zimmer {
    LinkedList <Schueler> belegung;
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
    
    public LinkedList getBelegungName(){
        LinkedList<String> belegung = new LinkedList<String>();
        for(Schueler s: this.belegung){
            belegung.add(s.getName());
        }
        return belegung;
    }
    
    public boolean istInZimmer(Schueler s){
        for(Schueler schueler: belegung){
            if(s == schueler)
                return true;
        }
        return false;
    }
    
}
