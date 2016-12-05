/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package junglespeedserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JULIEN
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        ServerSocket conn = null;
        Socket comm = null;
        ServerThread t;
        SpoolJoueur joueurs = new SpoolJoueur();
        SpoolPartie parties = new SpoolPartie();
        try {
            conn = new ServerSocket(1234);
        }
        catch(IOException e) {
            System.out.println("pb creation serveur : "+e.toString());
            System.exit(1) ;
        }
        try {
            while(true) {
                comm = conn.accept();
                System.out.println(joueurs);
                System.out.println("\tDebug : nouvelle connexion acceptée");
                t = new ServerThread(comm,joueurs,parties);
                t.start();
            }
        }
        catch(IOException e) {
        System.out.println("pb connexion client : "+e.toString());
        }
        finally{
            try {      
                conn.close();
            } catch (IOException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 
    
}
