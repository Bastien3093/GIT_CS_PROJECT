/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package junglespeedserver;

/**
 *
 * @author JULIEN
 */
public class Joueur {
    private String pseudo;
    public Joueur(String pseudo){
        this.pseudo = pseudo;
    }
    
    public String getPseudo(){
        return this.pseudo;
    }
}
