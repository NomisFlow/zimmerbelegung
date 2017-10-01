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
public class Zimmerbelegung {
    LinkedList<Schueler> alleSchueler;
    LinkedList<Zimmer> alleZimmer;
         
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Zimmerbelegung z = new Zimmerbelegung(testCase2());
    }
    
    public Zimmerbelegung(LinkedList<Schueler> s){ 
        alleSchueler = s;
        alleZimmer = new LinkedList<Zimmer>();
        sortiere(alleSchueler);
    }
    

    public void sortiere(LinkedList<Schueler> alleSchueler){
        for(Schueler s: alleSchueler){
            LinkedList<Schueler> favs = new LinkedList<Schueler>();
            for(Schueler s1: s.getFavorites()){
                favs.add(s1);
            }
            hinzufuegen(s, null);
            for(Schueler s1: favs){
                if(s1.getZimmer() != s.getZimmer() && s1.getZimmer() != null)
                    break;
                else{
                    hinzufuegen(s1, s.getZimmer());
                }                                                 
    
            }
        }
        check();
    }
    
    public boolean hinzufuegen(Schueler s, Zimmer z){
        boolean taken = false;
        if(z!=null){
            for(Zimmer zimmer: alleZimmer){
                if(zimmer.istInZimmer(s) && zimmer != z)
                    taken = true;
            }
            if(!taken && !z.istInZimmer(s)){
                z.addSchueler(s);
                return true;
            }
            
        }
        for(Zimmer zimmer: alleZimmer){
            if (zimmer.istInZimmer(s)){
                taken = true;
            }
        }
        if(!taken){  
            Zimmer z1 = new Zimmer(); 
            z1.addSchueler(s);
            alleZimmer.add(z1);
            return true;
        }
        return false;
    }

    public String check(){
        for(Zimmer z: alleZimmer){
            LinkedList<Schueler> belegungZimmer = z.getBelegung();
            for(Schueler s: belegungZimmer){
                for(Schueler s1: s.getDislikes()){
                    if(z.istInZimmer(s1)){
                        System.out.println("Keine Einteilung möglich.");
                        return "Keine Einteilung möglich";
                    }
                }
            }
        }
        String belegung = "";
        for(Zimmer z: alleZimmer){
            belegung = belegung + z.getBelegungName();
        }
        System.out.println(belegung);
        return belegung;
    }
    
    public static LinkedList<Schueler> testCase(){
        Schueler s1 = new Schueler("Alina");
        Schueler s2 = new Schueler("Emma");
        Schueler s3 = new Schueler("Lara");
        Schueler s4 = new Schueler("Lilli");        
        Schueler s5 = new Schueler("Mia");
        Schueler s6 = new Schueler("Zoe");  
        
        s1.addFavorite(s4);
        s5.addFavorite(s2);
        s5.addFavorite(s6);
        s6.addFavorite(s5);
        
        s2.addDisliked(s1);
        s3.addDisliked(s2);
        s4.addDisliked(s3);
        s6.addDisliked(s1);
        
        LinkedList<Schueler> s = new LinkedList<Schueler>();
        s.add(s1);
        s.add(s2);
        s.add(s3);
        s.add(s4);
        s.add(s5);
        s.add(s6);
        
        return s;

    }
    
    public static LinkedList<Schueler> testCase2(){
        Schueler s1 = new Schueler("Alina");
        Schueler s2 = new Schueler("Emma");
        Schueler s3 = new Schueler("Lara");
        Schueler s4 = new Schueler("Lilli");        
        Schueler s5 = new Schueler("Mia");
        Schueler s6 = new Schueler("Zoe");  
        
        s1.addFavorite(s4);
        s1.addFavorite(s3);
        s5.addFavorite(s2);
        s5.addFavorite(s6);
        s6.addFavorite(s5);
        
        s2.addDisliked(s1);
        s3.addDisliked(s2);
        s4.addDisliked(s3);
        s6.addDisliked(s1);
        
        LinkedList<Schueler> s = new LinkedList<Schueler>();
        s.add(s1);
        s.add(s2);
        s.add(s3);
        s.add(s4);
        s.add(s5);
        s.add(s6);
        
        return s;

    }
    
    
    
    
}
