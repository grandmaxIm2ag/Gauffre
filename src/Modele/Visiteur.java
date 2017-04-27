/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import Joueurs.*;
/**
 *
 * @author grandmax
 */
public abstract class Visiteur {
    
    public boolean visite(ComposantGraphique c){
        return false;
    }
    
    public boolean visite(Case c){
        return visite((ComposantGraphique) c);
    }
    public boolean visite(Poison c){
        return visite((ComposantGraphique) c);
    }
    public boolean visite(Plateau c){
        return visite((ComposantGraphique) c);
    }
    public boolean visite(Joueur c){
        return visite((ComposantGraphique) c);
    }
    public boolean visite(Message c){
        return visite((ComposantGraphique) c);
    }
    
}
