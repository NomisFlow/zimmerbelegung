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
public class Schueler {
    LinkedList<Schueler> favorites;
    LinkedList<Schueler> dislikes;
    String name;
    Zimmer zimmer;
    
    public Schueler(String name){
        this.name = name;
        favorites = new LinkedList<Schueler>();
        dislikes = new LinkedList <Schueler>();
    }
    
    void addFavorite(Schueler p){
        favorites.add(p);
    }
    void addDisliked(Schueler p){
        dislikes.add(p);
    }
    
    LinkedList<Schueler> getFavorites(){
        return favorites;
    }
    
    LinkedList<Schueler> getDislikes(){
        return dislikes;
    }
    
    public String getName(){
        return name;
    }
    void setZimmer(Zimmer z){
        zimmer = z;
    }
    Zimmer getZimmer(){
        return zimmer;
    }
    
}
