/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Share;

import java.util.ArrayList;

/**
 *
 * @author JULIEN
 */
public class Requete {
    public String type;
    public int argument;
    
    
    
    public Requete(String type, int argument){
        this.type=type;
        this.argument = argument;
    }
    public String getType(){
        return type;
    }
    public int getArguments(){
        return argument;
    }
    
    
    public String toSting(){
        return ("Type : "+type+" - Args = "+argument);
    }
}
