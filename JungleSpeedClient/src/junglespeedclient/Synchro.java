package junglespeedclient;
// instance de Synchro : objet partagé entre les instances de ThreadCom et JungleIG
// sert à la synchronisation des threads EDT (Event Dispatching Thread) et ThreadCom 

public class Synchro
{
	private boolean demandeConnexion;
        private String pseudo;
        private String ip;
        private int choix;
        
	public Synchro(){
            demandeConnexion = false;
	}
	
	public synchronized void attendreDemandeConnexion(){
		while (!demandeConnexion){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		demandeConnexion = false;	
	}
        
	public synchronized void signalerDemandeConnexion(String pseudo,String ip){
		demandeConnexion = true;
                this.pseudo = pseudo;
                this.ip = ip;
		notify();
	}
        
        public synchronized void signalerReceptionRequete(int requete){
            demandeConnexion = true; 
            this.choix = requete;
            notify();
        }
        
        public synchronized String getIP(){
            return this.ip;
        }
        
        public synchronized String getPseudo(){
            return this.pseudo;
        }    
        
        public synchronized int getChoix(){
            return this.choix;
        }
}

