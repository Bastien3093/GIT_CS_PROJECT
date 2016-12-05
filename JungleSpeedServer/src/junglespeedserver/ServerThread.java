/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package junglespeedserver;

import Share.Requete;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JULIEN
 */
public class ServerThread extends Thread {
    private Socket sockComm = null;
    private SpoolJoueur joueurs;
    private SpoolPartie parties;
    private Joueur j;
    
    public ServerThread(Socket comm,SpoolJoueur joueurs,SpoolPartie parties) {
        this.sockComm = comm;
        this.joueurs = joueurs;
        this.parties = parties;
    }
    
    @Override
     public void run() {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        Requete req = null;
        try{
            oos = new ObjectOutputStream(sockComm.getOutputStream());
            ois = new ObjectInputStream(sockComm.getInputStream());
           
            while(sockComm.isConnected()){
                boolean available;
                String pseudo;
                do{
                    pseudo = ois.readUTF();
                    System.out.println("Pseudo recu "+pseudo);
                    available = joueurs.pseudoAvailable(pseudo);
                    oos.writeBoolean(available);
                    oos.flush();
                    System.out.println("Boolean envoyé "+available);
                }while(!available);
                this.j = new Joueur(pseudo);
                joueurs.addJoueur(j);
			
                System.out.println("Attente reception requete");
            req = (Requete)ois.readObject();	
                System.out.println(req);
                
                
				
            }
        //... utilisation de oos et ois pour envoyer/recevoir des objets
        }
        catch(IOException e) {
            System.out.println("Joueur "+j.getPseudo()+" déconnecté");
            joueurs.removeJoueur(j);
        }
        catch(ClassNotFoundException e) {
            System.out.println("Joueur "+j.getPseudo()+" déconnecté");
            joueurs.removeJoueur(j);
        }
        finally{        
            try {
                if(oos != null)
                    oos.close();
                if(ois != null)
                    ois.close();
            } catch (IOException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 
    
}
