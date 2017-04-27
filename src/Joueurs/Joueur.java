/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Joueurs;

import Modele.*;
/**
 *
 * @author grandmax
 */
public abstract class Joueur extends ComposantGraphique{
    int score;
    String nom;
    
    public Joueur(int x, int y, int larg, int haut) {
        super(x, y, larg, haut);
        score = 0;
    }

    @Override
    public boolean accept() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
