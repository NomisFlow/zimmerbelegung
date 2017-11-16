package zimmerbelegung;

/**
 * @author simonwolf
 * @author simonbothe
 */

import java.util.LinkedList;

public class BelegungsTest {
    
    public static void main(String[] args){
        //moeglicherTestFall();
        //unmoeglicherTestFall();
        Playground p = new Playground("zimmerbelegung1");
        
    }

    public static void moeglicherTestFall(){
        /*Funktionierender Fall*/
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
        
        System.out.println(new Zimmerbelegung(s).sortiere());
    }
    
    public static void unmoeglicherTestFall(){
        /*Nicht funktionierender Fall*/
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
        
        System.out.println(new Zimmerbelegung(s).sortiere());
    }
}
