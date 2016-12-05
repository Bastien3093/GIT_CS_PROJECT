/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package junglespeedserver;

import java.util.*;

/**
 *
 * @author JULIEN
 */
public class SpoolJoueur {
    private ArrayList<Joueur> joueurs;
    public SpoolJoueur(){
        joueurs = new ArrayList();
    }
    
    public synchronized boolean pseudoAvailable(String pseudo){
        boolean available = true;
        for(Joueur j : joueurs){
            if(j.getPseudo().equals(pseudo))
                available = false;
        }
        return available;
    }
    
    public synchronized void addJoueur(Joueur j){
       joueurs.add(j);
    }
    
    public synchronized void removeJoueur(Joueur j){
       joueurs.remove(j);
    }
    
    @Override
    public String toString(){
        String list="";
        for( Joueur j : joueurs){
            list+=j.getPseudo()+"\n";
        }
        return list;
    }
}
