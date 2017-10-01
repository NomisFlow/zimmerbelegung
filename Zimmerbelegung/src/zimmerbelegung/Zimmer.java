/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zimmerbelegung;

import java.util.LinkedList;

/**
 *
 * @author simonwolf
 */
class Zimmer {
    LinkedList <Schueler> belegung;
    public Zimmer(){
        belegung = new LinkedList<Schueler>();
    }
    
    public void addSchueler(Schueler s){
        belegung.add(s);
        s.setZimmer(this);
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
