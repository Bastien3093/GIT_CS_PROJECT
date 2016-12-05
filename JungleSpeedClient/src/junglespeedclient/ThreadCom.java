package junglespeedclient;
// classe qui gère les échanges de messages avec l'application serveur.

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ThreadCom implements Runnable{
	private JungleIG ig;
	private Synchro sync;

	public  ThreadCom(Synchro s, JungleIG i){
		ig = i;
		sync = s;
	}
	public void run(){	
		// Dans cette méthode run(), toutes les modifications de l'interface graphique
		// doivent être réalisées via la méthode invokeLater() de la classe SwingUtilities
            int portServ = 1234;
            Socket sockComm = null;
            ObjectOutputStream oos = null;
            ObjectInputStream ois = null;
            boolean sucessConnect = false;
            while(!sucessConnect){
                sync.attendreDemandeConnexion();
                String ipServ = sync.getIP();
                try{
                    sockComm = new Socket();
                    sockComm.connect(new InetSocketAddress(ipServ,portServ));
                    sucessConnect = true;
                }catch(IOException e){sync.attendreDemandeConnexion();}
            }
            try {
                //Creates a stream socket and connects it to the specified port number
                //at the specified IP address.
                oos = new ObjectOutputStream(new BufferedOutputStream(sockComm.getOutputStream()));
                oos.flush();
                ois = new ObjectInputStream(new BufferedInputStream(sockComm.getInputStream()));
                boolean result;
                String pseudo;
                do{
                   pseudo = sync.getPseudo();
                   oos.writeUTF(pseudo);
                   oos.flush();
                   System.out.println("Pseudo envoyé "+pseudo);
                   result = ois.readBoolean();
                   System.out.println("Resultat "+result);
                   if(result){
                        javax.swing.SwingUtilities.invokeLater( new Runnable() {
                            public void run() {
                                ig.setInitPanel();
                            }
                        });	
                   }
                   else{
                       sync.attendreDemandeConnexion();
                   }
                }while(!result);
                int choix;
                do{
                    sync.attendreDemandeConnexion();
                    choix = sync.getChoix();
                    switch(choix){
                        case 1: //listPartie
                            System.out.println("Lister Partie");
                            break;
                        case 2: //CreateParty
                            System.out.println("Créer Partie");
                            break;
                        case 3://JoinParty
                            System.out.println("Join Partie");
                            break;
                        default:
                            System.out.println("Error");
                            break;

                    }
                }
                while(choix > 3 || choix < 1 );
            }
            catch(IOException e) {
                e.printStackTrace();
                System.out.println(e.toString());
            }
            finally {
                try {
                    if (sockComm != null)
                        sockComm.close();
                    if(oos != null)
                        oos.close();
                    if(ois != null)
                        ois.close();
                }
                catch(IOException e) {
                    System.out.println("Erreur IO");
                }
            }
	}
}
