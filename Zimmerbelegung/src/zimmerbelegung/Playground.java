/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zimmerbelegung;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author simonwolf
 * @author simonbothe
 */
public class Playground {
    public Playground(String s){
        einlesen(s);
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
            s.add(schueler);  
        }
        
        int i = 0;  //Zählvariable um durch Liste aller Schüler zu iterieren.
        //Füge Positiv-/Negativliste zu jedem Schüler hinzu
        for(String e: positivListen){
            String[] strArr = e.split(" "); //Array nötig um split-Methode zu benutzen
            ArrayList<String> parts = new ArrayList<String>(Arrays.asList(strArr)); //jedoch restliche Handhabung mit einer Liste einfacher
            parts.remove(0); //Pluszeichen wird entfernt
            
            //jeder Eintrag in 'parts' wird mit dem Namen jeden vorhandenen Schülers verglichen
            for(String str: parts){ 
                for(Schueler sch: s){
                    if(sch.getName().equals(str)){
                        s.get(i).addFavorite(sch);
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
                for(Schueler sch: s){
                    if(sch.getName().equals(str)){
                        s.get(i).addDisliked(sch);
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
   
}


