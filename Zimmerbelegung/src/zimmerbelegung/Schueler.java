package zimmerbelegung;

import java.util.LinkedList;

/**
 * @author simonwolf
 * @author simonbothe
 */

public class Schueler {
    LinkedList<Schueler> favorites;
    LinkedList<Schueler> dislikes;
    String name;
    
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
   
}
