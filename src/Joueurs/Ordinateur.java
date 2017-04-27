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
    
    public Ordinateur(int x, int y, int larg, int haut, Plateau p) {
        super(x, y, larg, haut);
        this.p = p;
    }
    
}
