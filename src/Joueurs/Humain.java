/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Joueurs;

/**
 *
 * @author grandmax
 */
public class Humain extends Joueur{

    public Humain(int x, int y, int larg, int haut, String n) {
        super(x, y, larg, haut);
        nom = n;
    }
    
}
