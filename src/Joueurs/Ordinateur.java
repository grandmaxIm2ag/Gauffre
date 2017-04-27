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
public class Ordinateur extends Joueur{
    Plateau p;
    
    public Ordinateur(int x, int y, int larg, int haut, boolean main, Plateau p) {
        super(x, y, larg, haut, main);
        this.p = p;
    }

    @Override
    public boolean equals(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
