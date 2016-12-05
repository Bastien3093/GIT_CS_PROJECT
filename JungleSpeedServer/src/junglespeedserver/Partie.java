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
public class Partie {
    private String nomPartie;
    private SpoolJoueur joueurs;
    
    public Partie(String nom,Joueur hote){
        this.joueurs = new SpoolJoueur();
        this.addPlayer(hote);
    }
    
    public synchronized void addPlayer(Joueur j){
        this.joueurs.addJoueur(j);
    } 
    public synchronized void removePlayer(Joueur j){
        this.joueurs.removeJoueur(j);
    } 
}
