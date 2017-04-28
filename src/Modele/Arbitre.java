/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import Joueurs.*;
import Modele.Ensembles.*;
/**
 *
 * @author grandmax
 */
public class Arbitre {
    
    public final static int JvJ = 0;
    public final static int JvIA = 1;
    public final static int J1 = 0;
    public final static int J2 = 1;
    
    Plateau p;
    Joueur joueurs[];
    int jCourant, type;
    Chargeur c;
    LIFO<String> historique;
    
    public Arbitre(int t){
        joueurs = new Joueur[2];
        jCourant=0;
        c = new Chargeur();
        historique = new LIFO();
        type = t;
        
    }
    
    public void init(){
        c.init();
        p=c.charger();
        
        switch(type){
            case JvJ:
                joueurs[0] = new Humain(p.tailleInitiale()+1, 0, 5, 5, true, "Joueur1");
                joueurs[1] = new Humain(p.tailleInitiale()+1, 0, 5, 5, true, "Joueur2");
                break;
            case JvIA:
                joueurs[0] = new Humain(p.tailleInitiale()+1, 0, 5, 5, true, "Joueur1");
                joueurs[1] = new Ordinateur(p.tailleInitiale()+1, 0, 5, 5, true);
                break;
            default:
                break;
                
        }
    }
    public void init(String plateau){
        c.init(plateau);
        p = c.charger();
        
        switch(type){
            case JvJ:
                joueurs[0] = new Humain(p.tailleInitiale()+1, 0, 5, 5, true, "Joueur1");
                joueurs[1] = new Humain(p.tailleInitiale()+1, 0, 5, 5, true, "Joueur2");
                break;
            case JvIA:
                joueurs[0] = new Humain(p.tailleInitiale()+1, 0, 5, 5, true, "Joueur1");
                joueurs[1] = new Ordinateur(p.tailleInitiale()+1, 0, 5, 5, true);
                break;
            default:
                break;
        }
    }
    
    public Plateau plateau(){
        return p;
    }
    
    public Plateau joue(float x, float y){
        int px = (int)x;
        int py = (int)y;
        Point tmp = new Point(px, py);
        
        historique.inserer(p.observable().historique(tmp));
        
        p.maj(tmp);
        
        return p;
    }
    
    public void help(){
        
    }
    
    public void precedent(){
        if(!historique.estVide()){    
            String[] cases = historique.extraire().split(":");
            for(int i=0; i<cases.length; i++){
                Point tmp = new Point(cases[i]);
                p.ajoutComposant(new Case(tmp.x(), tmp.y(), 1, 1));
            }
        } 
    }
    
    public void nouvellePartie(){
        init();
    }
    
    public void sauvegarde(){
        
    }
}
