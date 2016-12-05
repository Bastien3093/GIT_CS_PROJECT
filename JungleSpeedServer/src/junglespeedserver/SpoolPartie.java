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
public class SpoolPartie {
    private ArrayList<Partie> parties;
    public SpoolPartie(){
        this.parties = new ArrayList();
    }
    
    public synchronized void addPartie(Partie p){
        this.parties.add(p);
    }
    
    public synchronized void removePartie(Partie p){
        this.parties.remove(p);
    }
}
